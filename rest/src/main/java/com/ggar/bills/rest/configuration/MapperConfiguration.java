package com.ggar.bills.rest.configuration;

import com.ggar.bills.rest.mapper.UserMapper;
import com.ggar.bills.rest.mapper.UserMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}

}
