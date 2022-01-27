package com.splitxpense.sharexpense.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseModel{

  @ManyToOne
  private User admin;

  @ManyToMany
  private List<User> members;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Expense> expenses;

  private String name;
}
