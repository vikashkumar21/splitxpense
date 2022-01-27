package com.splitxpense.sharexpense.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequestDto extends AuthenticatedRequestDto{
  private String name;
  private List<Long> memberIds;
}
