package com.ggar.bills.rest.configuration;

import com.ggar.bills.core.api.AccountApi;
import com.ggar.bills.core.api.ImmutableAccountApi;
import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.ImmutableAccount;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.port.FindAllEntitiesPort;
import com.ggar.bills.core.port.FindEntityByFilterPort;
import com.ggar.bills.core.port.FindEntityByIdPort;
import com.ggar.bills.core.port.SaveOrUpdateEntityPort;
import com.ggar.bills.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AccountApiConfiguration {

	@Bean
	public AccountApi accountApi(
		FindEntityApiComponent<Account, Id> findEntityApiComponent,
		ExistsEntityApiComponent<Account, Id> existsEntityApiComponent,
		SaveOrUpdateEntityApiComponent<Account, Id> saveOrUpdateEntityApiComponent,
		FindAllEntitiesApiComponent<Account, Id> findAllEntitiesApiComponent
	) {
		return new ImmutableAccountApi(findEntityApiComponent, existsEntityApiComponent, saveOrUpdateEntityApiComponent, findAllEntitiesApiComponent);
	}

	@Bean
	public FindEntityApiComponent<Account, Id> findAccountApiComponent(FindEntityByIdPort<Account, Id> findEntityByIdPort, FindEntityByFilterPort<Account, Id> findEntityByFilterPort) {
		return ImmutableFindEntityApiComponent.of(findEntityByIdPort, findEntityByFilterPort);
	}

	@Bean
	public ExistsEntityApiComponent<Account, Id> existsAccountApiComponent(FindEntityByIdPort<Account, Id> findEntityByIdPort, FindEntityByFilterPort<Account, Id> findEntityByFilterPort) {
		return ImmutableExistsEntityApiComponent.<Account, Id>builder()
			.findEntityByFilterPort(findEntityByFilterPort)
			.findEntityByIdPort(findEntityByIdPort)
			.build();
	}

	@Bean
	public SaveOrUpdateEntityApiComponent<Account, Id> saveOrUpdateAccountApiComponent(FindEntityByIdPort<Account, Id> findEntityByIdPort, SaveOrUpdateEntityPort<Account> saveOrUpdateEntityPort) {
		return ImmutableSaveOrUpdateEntityApiComponent.<Account, Id>builder()
			.saveOrUpdateEntityPort(saveOrUpdateEntityPort)
			.findEntityByIdPort(findEntityByIdPort)
			.build();
	}

	@Bean
	public FindAllEntitiesApiComponent<Account, Id> findAllAccountsApiComponent(FindAllEntitiesPort<Account, Id> findAllEntitiesPort) {
		return ImmutableFindAllEntitiesApiComponent.<Account, Id>builder()
			.findAllEntitiesPort(findAllEntitiesPort)
			.build();
	}

	@Bean
	public FindEntityByIdPort<Account, Id> findAccountByIdPort() {
		return id -> Objects.requireNonNull(AccountRepository.cache.get(id));
	}

	@Bean
	public FindEntityByFilterPort<Account, Id> findAccountByFilterPort() {
		return filter -> AccountRepository.cache.values().stream().filter(filter).findAny().get();
	}

	@Bean
	public SaveOrUpdateEntityPort<Account> saveOrUpdateAccountPort() {
		return entity -> {
			Account account = ImmutableAccount.copyOf(entity).withId(Id.NEW);
			AccountRepository.cache.put(account.getId(), account);
			return AccountRepository.cache.get(account.getId());
		};
	}

	@Bean
	public FindAllEntitiesPort<Account, Id> findAllAccountsPort() {
		return e -> Flux.empty();
	}
}

class AccountRepository {
	public static final Map<Id, Account> cache = new ConcurrentHashMap<>();
}
