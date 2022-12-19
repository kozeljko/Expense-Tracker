package com.kozeljko.expensetracker.mapper;

import com.kozeljko.expensetracker.dto.UserDTO;
import com.kozeljko.expensetracker.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
