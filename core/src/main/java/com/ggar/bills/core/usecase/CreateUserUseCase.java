package com.ggar.bills.core.usecase;

import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.ImmutableUser;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.*;

@Value.Immutable
@Value.Style(allParameters = true)
public interface CreateUserUseCase extends UseCase<CreateUserUseCase.CreateUserUseCaseArguments, User> {

	UserApi userApi();

	@Override
	default Result<User> execute(CreateUserUseCaseArguments arguments) {
		Map<Id, Account> accounts = new HashMap<>(){{
			if (arguments.accounts().isPresent()) {
				for (Account account : arguments.accounts().get()) {
					this.put(account.getId(), account);
				}
			}
		}};
		return this.userApi().saveOrUpdate(ImmutableUser.builder()
			.id(ImmutableId.of(UUID.randomUUID()))
			.username(arguments.username())
			.hash(arguments.password())
			.accounts(accounts)
			.build()
		);
	}

	@Value.Immutable
	@Value.Style(allParameters = true)
	interface CreateUserUseCaseArguments extends UseCaseArguments {
		String username();
		String password();
		Optional<Set<Account>> accounts();
	}

}