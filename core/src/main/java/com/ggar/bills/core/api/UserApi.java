package com.ggar.bills.core.api;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.usecase.ImmutableRegisterAccountToUserArguments;
import com.ggar.bills.core.usecase.RegisterAccountToUserComponent;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allMandatoryParameters = true, of = "new")
public interface UserApi extends AbstractApi<User, Id> {

	RegisterAccountToUserComponent registerAccountToUserComponent();

	default Result<User> registerAccount(User user, Account account) {
		return this.registerAccountToUserComponent().execute(ImmutableRegisterAccountToUserArguments.builder()
			.user(user)
			.account(account)
			.build()
		);
	}

}
