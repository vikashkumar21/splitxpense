package com.splitxpense.sharexpense.dtos;

import com.splitxpense.sharexpense.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseResponseDto extends ResponseDto {
  private Expense expense;
}
