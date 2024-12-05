package io.devandre.furtmates.users.control.mapper;

import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
