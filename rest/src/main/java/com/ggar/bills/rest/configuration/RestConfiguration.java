package com.ggar.bills.rest.configuration;

import com.ggar.bills.core.api.HighLevelApi;
import com.ggar.bills.rest.controller.ImmutableUserController;
import com.ggar.bills.rest.controller.UserController;
import com.ggar.bills.rest.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

	@Bean
	public UserController userController(HighLevelApi highLevelApi, UserMapper userMapper) {
		return ImmutableUserController.builder()
			.highLevelApi(highLevelApi)
			.userMapper(userMapper)
			.build();
	}

}
