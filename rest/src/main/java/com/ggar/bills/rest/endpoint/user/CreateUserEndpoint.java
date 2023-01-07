package com.ggar.bills.rest.endpoint.user;

import com.ggar.bills.core.api.HighLevelApi;
import com.ggar.bills.core.model.User;
import com.ggar.bills.rest.mapper.UserMapper;
import com.ggar.bills.rest.model.UserDto;
import com.ggar.bills.rest.model.request.ImmutableCreateUserRequest;
import com.ggar.framework.core.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CreateUserEndpoint {

	UserMapper userMapper();
	HighLevelApi highLevelApi();

	@RequestMapping(method = RequestMethod.POST, path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	default HttpEntity<UserDto> createUser(@RequestBody ImmutableCreateUserRequest body) {
		Result<User> createUserResult = this.highLevelApi().createUser(body.username(), body.password());

		return !createUserResult.hasFailed() && createUserResult.isPresent()
			? new ResponseEntity<>(this.userMapper().map(createUserResult.get()), HttpStatus.CREATED)
			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
