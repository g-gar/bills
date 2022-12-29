package com.ggar.bills.rest.mapper;

import com.ggar.bills.core.model.ImmutableUser;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.rest.model.UserDto;
import org.mapstruct.*;

@Mapper
public interface UserMapper {

	@Mappings({
		@Mapping(target = "userId", expression = "java(user.getId().value())"),
		@Mapping(target = "username", expression = "java(user.username())")
	})
	UserDto map(User user);

	default User map(UserDto userDto) {
		return ImmutableUser.builder()
			.id(ImmutableId.builder().value(userDto.userId()).build())
			.username(userDto.username())
			.build();
	}

	@ObjectFactory
	default User create() {
		return ImmutableUser.builder().build();
	}
}
