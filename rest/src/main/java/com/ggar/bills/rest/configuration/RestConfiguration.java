package com.ggar.bills.rest.configuration;

import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.rest.controller.CreateUserController;
import com.ggar.bills.rest.controller.ImmutableCreateUserController;
import com.ggar.bills.rest.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

	@Bean
	public CreateUserController createUserController(UserApi userApi, UserMapper userMapper) {
		return new ImmutableCreateUserController(userApi, userMapper);
	}

}
