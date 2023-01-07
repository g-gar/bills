package com.ggar.bills.rest.endpoint.user;

import com.ggar.bills.core.api.HighLevelApi;
import com.ggar.bills.core.model.User;
import com.ggar.bills.rest.mapper.UserMapper;
import com.ggar.bills.rest.model.UserDto;
import com.ggar.framework.core.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

public interface FindUserEndpoint {

	UserMapper userMapper();
	HighLevelApi highLevelApi();

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	default HttpEntity<UserDto> findUser(@PathVariable UUID id) {
		HttpEntity<UserDto> result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Result<User> findUserResult = this.highLevelApi().findUser(id);
		if (findUserResult.isPresent() && !findUserResult.hasFailed()) {
			result = new ResponseEntity<>(this.userMapper().map(findUserResult.get()), HttpStatus.OK);
		}

		return result;
	}

}
