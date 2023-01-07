package com.ggar.bills.core.usecase;

import com.ggar.bills.core.api.AccountApi;
import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.ImmutableAccount;
import com.ggar.bills.core.model.Payment;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.*;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(allMandatoryParameters = true)
public interface CreateAccountUseCase extends UseCase<CreateAccountUseCase.CreateAccountArguments, User> {

	UserApi getUserApi();
	AccountApi getAccountApi();

	@Override
	default Result<User> execute(CreateAccountArguments arguments) {
		Result<User> result = null;
		Id userId = arguments.userId();
		result = this.getUserApi().find(userId);
		if (!result.hasFailed()) {
			Account account = ImmutableAccount.builder()
				.id(ImmutableId.builder().build())
				.name(arguments.accountName())
				.payment(arguments.payment())
				.build();
			Result<Account> createAccountResult = this.getAccountApi().saveOrUpdate(account);
			if (!createAccountResult.hasFailed() && createAccountResult.isPresent()) {
				result = this.getUserApi().registerAccount(result.get(), createAccountResult.get());
			} else {
				result = Result.failed(createAccountResult.getException().get());
			}
		}
		return result;
	}

	@Value.Immutable
	@Value.Style(optionalAcceptNullable = true, allParameters = true)
	interface CreateAccountArguments extends UseCaseArguments {
		Id userId();
		AccountName accountName();
		@Value.Default
		@Value.Parameter(value = false)
		default Optional<AccountBalance> accountBalance() {
			return Optional.of(ImmutableAccountBalance.of(0d));
		};
		Payment payment();
	}

}
