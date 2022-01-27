package com.splitxpense.sharexpense.service.settleup;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Transaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("bruteForceSettleUpStrategy")
public class BruteForceSettleUpStrategy implements SettleUpStrategy {

  @Override
  public List<Transaction> settle(List<Expense> expenses) {
    Map<Long, Map<Long, Long>> mapUserToUserToAmount = new HashMap<>();

    for(Expense expense : expenses) {
      //make single paidBy
    }
    return null;
  }
}
