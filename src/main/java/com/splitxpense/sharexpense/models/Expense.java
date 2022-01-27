package com.splitxpense.sharexpense.models;

import java.util.Currency;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "expenses")
public class Expense extends BaseModel {
  private String description;

  @ManyToOne
  private User createdBy;

  private long amount;

  private Currency currency;

  @ElementCollection
  Map<User, Long> paidBy;

  @ElementCollection
  Map<User, Long> owedBy;
}
