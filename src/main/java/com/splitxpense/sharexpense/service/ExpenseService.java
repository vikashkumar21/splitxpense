package com.splitxpense.sharexpense.service;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Group;
import com.splitxpense.sharexpense.repository.ExpenseRepository;
import com.splitxpense.sharexpense.repository.GroupRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private GroupRepository groupRepository;

  public Expense createExpense(Expense expense){
    return expenseRepository.save(expense);
  }

  public Group createGroupExpense(Long groupId, Expense expense) {
    //To-do create group expense
    Expense savedExpense = expenseRepository.save(expense);
    Optional<Group> groupOptional = groupRepository.findById(groupId);
    if (groupOptional.isEmpty()) {
      return null;
    }
    Group group = groupOptional.get();
    group.getExpenses().add(savedExpense);
    return groupRepository.save(group);
  }
}
