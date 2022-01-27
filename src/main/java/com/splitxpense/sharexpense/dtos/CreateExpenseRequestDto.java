package com.splitxpense.sharexpense.dtos;

import java.util.Currency;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseRequestDto extends AuthenticatedRequestDto{

  private String description;
  private long amount;
  private Currency currency;
  private Map<Long,Long> paidBy;
  private Map<Long, Long> owedBy;
}
