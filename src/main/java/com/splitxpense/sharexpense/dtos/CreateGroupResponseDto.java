package com.splitxpense.sharexpense.dtos;

import com.splitxpense.sharexpense.models.Group;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponseDto extends ResponseDto {

  private Group group;

}
