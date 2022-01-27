package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupExpenseRequestDto {

  private CreateExpenseRequestDto expense;
  private Long groupId;
}
