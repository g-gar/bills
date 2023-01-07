package com.ggar.bills.core.api;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.Payment;
import com.ggar.bills.core.model.field.Id;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true, of = "new")
public interface AccountApi extends AbstractApi<Account, Id> {


	default Result<Account> registerPaymentToAccount(Account account, Payment payment) {
		return null;
	}

}
