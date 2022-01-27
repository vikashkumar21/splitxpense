package com.splitxpense.sharexpense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ResponseDto {
  private long requestId;
  private Status status;
}
