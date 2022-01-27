package com.splitxpense.sharexpense.service.settleup;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Transaction;
import com.splitxpense.sharexpense.models.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service("greedySettleUpStrategy")
//@Qualifier("greedySettleUpStrategy")
public class GreedySettleUpStrategy implements SettleUpStrategy {

  @Override
  public List<Transaction> settle(List<Expense> expenses) {
    Map<Long, Long> userOwedAmount = new HashMap<>();

    for (Expense expense : expenses) {
      for (User user : expense.getOwedBy().keySet()) {
        if (!userOwedAmount.containsKey(user.getId())) {
          userOwedAmount.put(user.getId(), 0L);
        }
        userOwedAmount.put(user.getId(),
            userOwedAmount.get(user.getId()) + expense.getOwedBy().get(user));
      }

      for (User user : expense.getPaidBy().keySet()) {
        if (!userOwedAmount.containsKey(user.getId())) {
          userOwedAmount.put(user.getId(), 0L);
        }
        userOwedAmount.put(user.getId(),
            userOwedAmount.get(user.getId()) - expense.getPaidBy().get(user));
      }
    }
      TreeSet<Pair<Long, Long>> set = new TreeSet<>(new Comparator<Pair<Long, Long>>() {
        @Override
        public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
          return (int) (o1.getFirst()-o2.getFirst());
        }
      }); //amount -> userId

      for (Long userId : userOwedAmount.keySet()) {
        set.add(Pair.of(userOwedAmount.get(userId), userId));
      }
      List<Transaction> transactions = new ArrayList<>();
      while (set.size() > 1) {
        Pair<Long, Long> smallestPair = set.first();
        Pair<Long, Long> largestPair = set.last();
        Transaction transaction = new Transaction();
        transaction.setFromId(largestPair.getSecond());
        transaction.setToId(smallestPair.getSecond());
        transaction.setAmount(largestPair.getFirst());

        set.remove(largestPair);
        set.remove(smallestPair);
        set.add(Pair.of(smallestPair.getFirst()+largestPair.getFirst(), smallestPair.getSecond()));
        transactions.add(transaction);
      }
    return transactions;
  }
}
