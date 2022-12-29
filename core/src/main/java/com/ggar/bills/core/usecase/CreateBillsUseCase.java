package com.ggar.bills.core.usecase;

import com.ggar.bills.core.api.AccountApi;
import com.ggar.bills.core.api.BillApi;
import com.ggar.bills.core.model.Bill;
import com.ggar.bills.core.model.field.Id;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(allParameters = true)
public interface CreateBillsUseCase {

	AccountApi accountApi();
	BillApi billApi();

	@Value.Immutable
	@Value.Style(allParameters = true, optionalAcceptNullable = true)
	interface CreateBillsUseCaseArguments extends UseCaseArguments {
		Id accountId();
		List<Bill> bills();
	}
}
