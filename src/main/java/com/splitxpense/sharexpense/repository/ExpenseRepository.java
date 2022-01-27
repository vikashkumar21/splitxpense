package com.splitxpense.sharexpense.repository;

import com.splitxpense.sharexpense.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  Expense save(Expense expense);
}
