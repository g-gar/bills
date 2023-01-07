package com.ggar.bills.rest.configuration;

import com.ggar.bills.core.api.AccountApi;
import com.ggar.bills.core.api.HighLevelApi;
import com.ggar.bills.core.api.ImmutableHighLevelApi;
import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonApiConfiguration {

	@Bean
	public HighLevelApi highLevelApi(UserApi userApi, AccountApi accountApi, CreateUserUseCase createUserUseCase, CreateAccountUseCase createAccountUseCase, CreateStoreUseCase createStoreUseCase) {
		return ImmutableHighLevelApi.builder()
			.userApi(userApi)
			.accountApi(accountApi)
			.createUserUseCase(createUserUseCase)
			.createAccountUseCase(createAccountUseCase)
			.createStore(createStoreUseCase)
			.build();
	}

	@Bean
	public CreateUserUseCase createUserUseCase(UserApi userApi) {
		return ImmutableCreateUserUseCase.of(userApi);
	}

	@Bean
	public CreateAccountUseCase createAccountUseCase(UserApi userApi, AccountApi accountApi) {
		return ImmutableCreateAccountUseCase.of(userApi, accountApi);
	}

	@Bean
	public CreateStoreUseCase createStoreUseCase() {
		return ImmutableCreateStoreUseCase.builder().build();
	}
}
