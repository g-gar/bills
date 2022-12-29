package com.ggar.bills.core.api;

import com.ggar.bills.core.model.*;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.usecase.CreateAccountUseCase;
import com.ggar.bills.core.usecase.CreateStoreUseCase;
import com.ggar.bills.core.usecase.CreateUserUseCase;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

@Value.Immutable
@Value.Style(allMandatoryParameters = true)
public interface HighLevelApi {

	CreateUserUseCase createUserUseCase();
	CreateAccountUseCase createAccountUseCase();
	CreateStoreUseCase createStore();

	default Result<Store> execute(CreateStoreUseCase.CreateStoreArguments createStoreArguments) {
		return this.createStore().execute(createStoreArguments);
	}
	default Result<User> execute(CreateAccountUseCase.CreateAccountArguments createAccountArguments) {
		return this.createAccountUseCase().execute(createAccountArguments);
	}

	default Result<Bill> createBills(Id userId, Id accountId, Map<Store, List<Bill>> bills) {
		return null;
	}

//	Flux<Account> findAccountsByUser(Id userId, Predicate<Account> filter);
//	Flux<Bill> findBillsByUser(Id userId, Predicate<Bill> filter);
//
//	Flux<Product> findProducts(Predicate<Product> filter);
//	Flux<Product> findProducts(Predicate<Account> accountFilter, Predicate<Bill> billFilter, Predicate<Product> productFilter);



}
