package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDto extends ResponseDto{
  private UserDto user;
}
