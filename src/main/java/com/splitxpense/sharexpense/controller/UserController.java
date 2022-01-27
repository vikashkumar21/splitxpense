package com.splitxpense.sharexpense.controller;

import com.splitxpense.sharexpense.dtos.RegisterUserRequestDto;
import com.splitxpense.sharexpense.dtos.RegisterUserResponseDto;
import com.splitxpense.sharexpense.dtos.Status;
import com.splitxpense.sharexpense.dtos.UpdateProfileRequestDto;
import com.splitxpense.sharexpense.dtos.UpdateProfileResponseDto;
import com.splitxpense.sharexpense.dtos.UserDto;
import com.splitxpense.sharexpense.models.User;
import com.splitxpense.sharexpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
  @Autowired
  private UserService userService;

  public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
    User user = userService.registerUser(request.getName(), request.getPhoneNumber(), request.getPassword());

    RegisterUserResponseDto response = new RegisterUserResponseDto();
    response.setUser(UserDto.from(user));
    response.setRequestId(1234);
    response.setStatus(Status.SUCCESS);

    return response;
  }

  public UpdateProfileResponseDto updateUserProfile(UpdateProfileRequestDto request){
    long id = request.getUserId();
    String name = request.getName();
    String phoneNumber = request.getPhoneNumber();
    String password = request.getPassword();
    User user = userService.updateUserProfile(id, name, phoneNumber, password);
    UpdateProfileResponseDto responseDto = new UpdateProfileResponseDto();
    responseDto.setUser(UserDto.from(user));
    responseDto.setStatus(Status.SUCCESS);
    return responseDto;
  }
}
