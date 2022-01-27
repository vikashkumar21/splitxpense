package com.splitxpense.sharexpense.service;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Group;
import com.splitxpense.sharexpense.models.Transaction;
import com.splitxpense.sharexpense.models.User;
import com.splitxpense.sharexpense.repository.GroupRepository;
import com.splitxpense.sharexpense.repository.UserRepository;
import com.splitxpense.sharexpense.service.settleup.SettleUpStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  @Qualifier("greedySettleUpStrategy") // use application.properties to pick settleup strategy
  private SettleUpStrategy settleUpStrategy;

  public Group createGroup(String name, long userId, List<Long> memberIds) {
    Group group = new Group();
    List<User> list = new ArrayList<>();
    for (Long id : memberIds){
      Optional<User> user = userRepository.findById(id);
      if(!user.isEmpty()) {
        list.add(user.get());
      }
    }
    Optional<User> user = userRepository.findById(userId);
    if(!user.isEmpty()) {
      group.setAdmin(user.get());
    }
    group.setName(name);
    group.setMembers(list);
    return groupRepository.save(group);
  }

  public List<Transaction> settleExpenses(Long groupId) {
    Optional<Group> group = groupRepository.findById(groupId);

    if(group.isEmpty()) {
      //throw new InvalidGroupIdException
    }
    List<Expense> expenses = group.get().getExpenses();
    List<Transaction> transactions = this.settleUpStrategy.settle(expenses);
    return null;
  }
}
