package com.ggar.bills.rest.controller;

import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.core.model.ImmutableUser;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.rest.mapper.UserMapper;
import com.ggar.bills.rest.model.UserDto;
import com.ggar.bills.rest.model.request.ImmutableCreateUserRequest;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "users")
@Value.Immutable
@Value.Style(allParameters = true, passAnnotations = {RequestMapping.class}, of = "new")
public interface CreateUserController {

	UserApi userApi();
	UserMapper userMapper();

	@RequestMapping(method = RequestMethod.POST, path = "{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	default HttpEntity<UserDto> execute(HttpServletRequest request, HttpServletResponse response, @PathVariable String username, @RequestBody ImmutableCreateUserRequest body) {
		User user = ImmutableUser.builder().build()
			.withId(ImmutableId.builder().build())
			.withUsername(username)
			.withHash(body.password());
		Result<User> createUserResult = this.userApi().saveOrUpdate(user);

		return !createUserResult.hasFailed() && createUserResult.get().isPresent()
			? new ResponseEntity<>(this.userMapper().map(createUserResult.get().get()), HttpStatus.CREATED)
			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
