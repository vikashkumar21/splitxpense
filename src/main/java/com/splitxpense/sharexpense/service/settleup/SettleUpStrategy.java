package com.splitxpense.sharexpense.service.settleup;

import com.splitxpense.sharexpense.models.Expense;
import com.splitxpense.sharexpense.models.Transaction;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface SettleUpStrategy {
  List<Transaction> settle(List<Expense> expenses);
}
