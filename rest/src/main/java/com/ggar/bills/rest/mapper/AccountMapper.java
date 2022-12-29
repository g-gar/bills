package com.ggar.bills.rest.mapper;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.ImmutableAccount;
import com.ggar.bills.core.model.ImmutablePayment;
import com.ggar.bills.core.model.field.AccountName;
import com.ggar.bills.core.model.field.ImmutableAccountBalance;
import com.ggar.bills.core.model.field.ImmutableAccountName;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.rest.model.AccountDto;
import org.mapstruct.*;

@Mapper(subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface AccountMapper {

	@Mappings({
		@Mapping(target = "accountId", expression = "java(account.getId().value())"),
		@Mapping(target = "paymentId", expression = "java(account.payment().getId().value())"),
		@Mapping(target = "balance", expression = "java(account.balance().value().get())"),
		@Mapping(target = "title", expression = "java(account.name().value().get())"),
	})
	AccountDto map(Account account);

	default Account map(AccountDto accountDto) {
		return ImmutableAccount.builder()
			.id(ImmutableId.builder().value(accountDto.accountId()).build())
			.name(ImmutableAccountName.builder().value(accountDto.title()).build())
			.balance(ImmutableAccountBalance.builder().value(accountDto.balance()).build())
			.payment(ImmutablePayment.builder().id(ImmutableId.builder().value(accountDto.paymentId()).build()).build())
			.build();
	}

	@ObjectFactory
	default Account create() {
		return ImmutableAccount.builder().build();
	}
}
