package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleGroupExpenseRequestDto extends AuthenticatedRequestDto {
  private long groupId;
}
