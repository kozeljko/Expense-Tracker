package com.kozeljko.expensetracker.mapper;

import com.kozeljko.expensetracker.dto.UserDTO;
import com.kozeljko.expensetracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);
}
