package com.authservice.core.mapper;

import com.authservice.core.dto.UserDTO;
import com.authservice.core.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapper extends BaseMapper<UserDTO, User> {
}
