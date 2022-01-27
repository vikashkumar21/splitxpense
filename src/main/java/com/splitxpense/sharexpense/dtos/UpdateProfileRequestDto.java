package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDto extends AuthenticatedRequestDto {
  private String name;
  private String phoneNumber;
  private String password;
}
