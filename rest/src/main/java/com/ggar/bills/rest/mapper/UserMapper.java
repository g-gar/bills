package com.ggar.bills.rest.mapper;

import com.ggar.bills.core.model.ImmutableUser;
import com.ggar.bills.core.model.User;
import com.ggar.bills.rest.model.UserDto;
import com.ggar.bills.rest.model.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

	@Mappings({
		@Mapping(target = "userId", expression = "java(user.getId().value())"),
		@Mapping(target = "username", expression = "java(user.username())"),
		@Mapping(target = "accounts", expression = "java(user.accounts())"),
		@Mapping(target = "putAccounts", ignore = true),
		@Mapping(target = "putAllAccounts", ignore = true)
	})
	UserDto map(User user);

	default User map(CreateUserRequest createUserRequest) {
		return ImmutableUser.of(createUserRequest.username(), createUserRequest.password());
	}

}
