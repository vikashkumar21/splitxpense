package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupExpenseResponseDto {
  CreateExpenseResponseDto expense;
  private Long groupId;
}
