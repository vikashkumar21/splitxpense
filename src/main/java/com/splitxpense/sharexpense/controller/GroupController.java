package com.splitxpense.sharexpense.controller;

import com.splitxpense.sharexpense.dtos.CreateGroupRequestDto;
import com.splitxpense.sharexpense.dtos.CreateGroupResponseDto;
import com.splitxpense.sharexpense.dtos.SettleGroupExpenseRequestDto;
import com.splitxpense.sharexpense.dtos.SettleGroupExpenseResponeDto;
import com.splitxpense.sharexpense.dtos.Status;
import com.splitxpense.sharexpense.models.Group;
import com.splitxpense.sharexpense.models.Transaction;
import com.splitxpense.sharexpense.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

  @Autowired
  private GroupService groupService;

  public CreateGroupResponseDto create(CreateGroupRequestDto request) {
    Group group = groupService.createGroup(request.getName(), request.getUserId(), request.getMemberIds());

    CreateGroupResponseDto response = new CreateGroupResponseDto();
    response.setGroup(group);
    response.setStatus(Status.SUCCESS);
    response.setRequestId(12345);
    return response;
  }
  public SettleGroupExpenseResponeDto settleExpenses(SettleGroupExpenseRequestDto request) {
    SettleGroupExpenseResponeDto response = new SettleGroupExpenseResponeDto();
    List<Transaction> transactions;
    transactions = this.groupService.settleExpenses(request.getGroupId());
    response.setTransactions(transactions);
    return response;
  }
}
