package com.splitxpense.sharexpense.controller;

import com.splitxpense.sharexpense.dtos.CreateExpenseRequestDto;
import com.splitxpense.sharexpense.dtos.CreateExpenseResponseDto;
import com.splitxpense.sharexpense.dtos.CreateGroupExpenseRequestDto;
import com.splitxpense.sharexpense.dtos.CreateGroupExpenseResponseDto;
import com.splitxpense.sharexpense.dtos.Status;
import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.User;
import com.splitxpense.sharexpense.repository.UserRepository;
import com.splitxpense.sharexpense.service.ExpenseService;
import com.splitxpense.sharexpense.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ExpenseService expenseService;

  public CreateExpenseResponseDto create(CreateExpenseRequestDto request) {
    CreateExpenseResponseDto response = new CreateExpenseResponseDto();
    Map<User, Long> owedUserAmounts =  new HashMap<>();
    for (Entry<Long, Long> idAmount : request.getOwedBy().entrySet()){
      Long id = idAmount.getKey();
      Long amount = idAmount.getValue();

      Optional<User> user = userRepository.findById(id);

      if(user.isEmpty()) {
        response.setStatus(Status.ERROR);
        return response;
      }
      owedUserAmounts.put(user.get(), amount);
    }

    Map<User, Long> paidUserAmounts =  new HashMap<>();
    for (Entry<Long, Long> idAmount: request.getPaidBy().entrySet()){
      Long id = idAmount.getKey();
      Long amount = idAmount.getValue();

      Optional<User> user = userRepository.findById(id);

      if(user.isEmpty()) {
        response.setStatus(Status.ERROR);
        return response;
      }
      paidUserAmounts.put(user.get(), amount);
    }

    Optional<User> createdByUser = userRepository.findById(request.getUserId());

    Expense expense = new Expense();
    expense.setCreatedBy(createdByUser.get());
    expense.setAmount(request.getAmount());
    expense.setCurrency(request.getCurrency());
    expense.setDescription(request.getDescription());
    expense.setOwedBy(owedUserAmounts);
    expense.setPaidBy(paidUserAmounts);

    expenseService.createExpense(expense);
    response.setExpense(expense);
    return response;
  }

  public CreateGroupExpenseResponseDto createGroupExpense(CreateGroupExpenseRequestDto groupRequest) {
    CreateGroupExpenseResponseDto groupResponse = new CreateGroupExpenseResponseDto();
    Long groupId = groupRequest.getGroupId();
    groupResponse.setGroupId(groupId);
    CreateExpenseRequestDto request = groupRequest.getExpense();

    CreateExpenseResponseDto response = new CreateExpenseResponseDto();
    Map<User, Long> owedUserAmounts =  new HashMap<>();
    for (Entry<Long, Long> idAmount : request.getOwedBy().entrySet()){
      Long id = idAmount.getKey();
      Long amount = idAmount.getValue();

      Optional<User> user = userRepository.findById(id);

      if(user.isEmpty()) {
        response.setStatus(Status.ERROR);
        groupResponse.setExpense(response);
        return groupResponse;
      }
      owedUserAmounts.put(user.get(), amount);
    }

    Map<User, Long> paidUserAmounts =  new HashMap<>();
    for (Entry<Long, Long> idAmount: request.getPaidBy().entrySet()){
      Long id = idAmount.getKey();
      Long amount = idAmount.getValue();

      Optional<User> user = userRepository.findById(id);

      if(user.isEmpty()) {
        response.setStatus(Status.ERROR);
        groupResponse.setExpense(response);
        return groupResponse;
      }
      paidUserAmounts.put(user.get(), amount);
    }

    Optional<User> createdByUser = userRepository.findById(request.getUserId());

    Expense expense = new Expense();
    expense.setCreatedBy(createdByUser.get());
    expense.setAmount(request.getAmount());
    expense.setCurrency(request.getCurrency());
    expense.setDescription(request.getDescription());
    expense.setOwedBy(owedUserAmounts);
    expense.setPaidBy(paidUserAmounts);

    expenseService.createGroupExpense(groupId, expense);
    return groupResponse;
  }
}
