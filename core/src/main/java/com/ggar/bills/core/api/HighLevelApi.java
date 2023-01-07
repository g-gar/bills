package com.ggar.bills.core.api;

import com.ggar.bills.core.model.*;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableAccountName;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.core.model.field.ImmutablePaymentTitle;
import com.ggar.bills.core.usecase.CreateAccountUseCase;
import com.ggar.bills.core.usecase.CreateStoreUseCase;
import com.ggar.bills.core.usecase.CreateUserUseCase;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;

import java.util.*;

@Value.Immutable
@Value.Style(allMandatoryParameters = true, of = "new")
public interface HighLevelApi {

	UserApi userApi();
	AccountApi accountApi();
	PaymentApi paymentApi();

	CreateUserUseCase createUserUseCase();
	CreateAccountUseCase createAccountUseCase();
	CreateStoreUseCase createStore();

	default Result<Payment> createPayment(String title) {
		return this.paymentApi().saveOrUpdate(ImmutablePayment.builder()
			.title(ImmutablePaymentTitle.of(title))
			.build()
		);
	}

	default Result<Account> createAccount(String accountName, UUID paymentId, UUID userId) {
		Result<Account> result = Result.failed(NullPointerException::new);

		Result<User> findUser = this.userApi().find(ImmutableId.of(userId));
		if(!findUser.hasFailed() && findUser.isPresent()) {
			Result<Payment> findPayment = this.paymentApi().find(ImmutableId.of(paymentId));
			if (!findPayment.hasFailed() && findPayment.isPresent()) {
				Result<Account> createAccount =  this.accountApi().saveOrUpdate(ImmutableAccount.builder()
					.name(ImmutableAccountName.of(accountName))
		            .payment(findPayment.get())
					.build()
				);
				if (!createAccount.hasFailed() && createAccount.isPresent()) {
					Result<User> registerAccount = this.userApi().registerAccount(findUser.get(), createAccount.get());
					if (!registerAccount.hasFailed() && registerAccount.isPresent()) {
						result = createAccount;
					}
				}
			}
		}

		return result;
	}

	default Result<User> createUser(String username, String password) {
		return this.userApi().saveOrUpdate(ImmutableUser.builder()
			.username(username)
			.hash(password)
			.build()
		);
	}

	default Result<User> findUser(UUID id) {
		return this.userApi().find(ImmutableId.of(id));
	}

	default Result<User> updateUserAccounts(UUID userId, Set<UUID> newAccountIds) {
		Result<User> result = Result.failed(NullPointerException::new);
		Map<Id, Account> accounts = null;
		Result<User> findUser = this.userApi().find(ImmutableId.of(userId));
		if (!findUser.hasFailed() && findUser.isPresent()) {
			accounts = new HashMap<>() {{
				putAll(findUser.get().accounts());
				for (UUID accountId : newAccountIds) {
					Result<Account> findAccount = HighLevelApi.this.accountApi().find(ImmutableId.of(accountId));
					if (!findAccount.hasFailed() && findAccount.isPresent()) {
						put(findAccount.get().getId(), findAccount.get());
					}
				}
			}};
			result = this.userApi().saveOrUpdate(ImmutableUser.copyOf(findUser.get()).withAccounts(accounts));
		}
		return result;
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
