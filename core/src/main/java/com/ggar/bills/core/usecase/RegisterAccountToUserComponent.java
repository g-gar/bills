package com.ggar.bills.core.usecase;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.port.RegisterAccountToUserPort;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface RegisterAccountToUserComponent extends UseCase<RegisterAccountToUserComponent.RegisterAccountToUserArguments, User> {

	RegisterAccountToUserPort registerAccountToUserPort();

	@Override
	default Result<User> execute(RegisterAccountToUserArguments arguments) {
		return Result.from(this.registerAccountToUserPort()::execute, arguments.userId(), arguments.account());
	}

	@Value.Immutable
	@Value.Style(allParameters = true, optionalAcceptNullable = true)
	interface RegisterAccountToUserArguments extends UseCaseArguments {
		Id userId();
		Account account();
	}
}
