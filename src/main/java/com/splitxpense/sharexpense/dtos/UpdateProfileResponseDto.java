package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResponseDto extends ResponseDto {
  private UserDto user;
}
