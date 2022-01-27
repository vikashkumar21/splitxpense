package com.splitxpense.sharexpense;

import com.splitxpense.sharexpense.controller.ExpenseController;
import com.splitxpense.sharexpense.controller.GroupController;
import com.splitxpense.sharexpense.controller.UserController;
import com.splitxpense.sharexpense.dtos.CreateExpenseRequestDto;
import com.splitxpense.sharexpense.dtos.CreateGroupExpenseRequestDto;
import com.splitxpense.sharexpense.dtos.CreateGroupRequestDto;
import com.splitxpense.sharexpense.dtos.RegisterUserRequestDto;
import com.splitxpense.sharexpense.dtos.UpdateProfileRequestDto;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SharexpenseApplication implements CommandLineRunner{
  @Autowired
  private UserController userController;

  @Autowired
  private ExpenseController expenseController;

  @Autowired
  private GroupController groupController;

  public static void main(String[] args) {
    SpringApplication.run(SharexpenseApplication.class, args);
  }

  //@Override
  public void run(String... args) throws Exception {
    /*System.out.println("====registering user=======");
    RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
    requestDto.setName("Vikash");
    requestDto.setPhoneNumber("12345");
    requestDto.setPassword("password");

    userController.registerUser(requestDto);*/

    System.out.println("====updating user=======");
    UpdateProfileRequestDto requestDto1 = new UpdateProfileRequestDto();
    requestDto1.setUserId(2);
    requestDto1.setName("Vikas");
    requestDto1.setPhoneNumber("12345");
    requestDto1.setPassword("password2");

    userController.updateUserProfile(requestDto1);

    System.out.println("=========creating group==========");
    CreateGroupRequestDto groupRequestDto = new CreateGroupRequestDto();
    groupRequestDto.setName("Avengers");
    groupRequestDto.setUserId(1);
    groupRequestDto.setMemberIds(List.of(1l,2l));

    groupController.create(groupRequestDto);

    System.out.println("==========creating normal expense============");

    String description = "Class charge";
    Map<Long, Long> paidBy = new HashMap<>();
    paidBy.put(1L, 100L);
    paidBy.put(2L, 30L);
    Map<Long, Long> owedBy = new HashMap<>();
    owedBy.put(3L, 130L);

    CreateExpenseRequestDto requestDto = new CreateExpenseRequestDto();
    requestDto.setAmount(130L);
    requestDto.setCurrency(Currency.getInstance("INR"));
    requestDto.setDescription(description);
    requestDto.setUserId(1L);
    requestDto.setOwedBy(owedBy);
    requestDto.setPaidBy(paidBy);

    expenseController.create(requestDto);

    System.out.println("==========creating group expense============");

    CreateGroupExpenseRequestDto groupExpenseRequestDto = new CreateGroupExpenseRequestDto();
    requestDto.getPaidBy().remove(2l);
    requestDto.getOwedBy().remove(3l);
    requestDto.getOwedBy().put(1l, 65l);
    requestDto.getOwedBy().put(2l, 65l);
    groupExpenseRequestDto.setGroupId(1l);
    groupExpenseRequestDto.setExpense(requestDto);

    expenseController.createGroupExpense(groupExpenseRequestDto);
  }
}
