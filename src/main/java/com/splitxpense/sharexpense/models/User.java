package com.splitxpense.sharexpense.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "users")
public class User extends BaseModel {
  private String name;
  private String phoneNumber;
  private String hashedPassword;
}
