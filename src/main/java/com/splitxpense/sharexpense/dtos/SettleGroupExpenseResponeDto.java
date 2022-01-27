package com.splitxpense.sharexpense.dtos;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Transaction;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleGroupExpenseResponeDto extends ResponseDto{
  private List<Transaction> transactions;
}
