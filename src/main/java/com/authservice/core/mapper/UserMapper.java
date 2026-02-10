package com.authservice.core.mapper;

import com.authservice.core.dto.UserDTO;
import com.authservice.core.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserMapper {

    public static UserDTO toDto(final User client) {
        return new UserDTO(client.getId(), client.getName(), client.getUsername(), client.getPassword(), client.getRole());
    }

    public static User toEntity(final UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
    }
}
