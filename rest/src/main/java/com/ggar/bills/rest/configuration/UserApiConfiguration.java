package com.ggar.bills.rest.configuration;

import com.ggar.bills.core.api.ImmutableUserApi;
import com.ggar.bills.core.api.UserApi;
import com.ggar.bills.core.model.ImmutableUser;
import com.ggar.bills.core.model.User;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.port.*;
import com.ggar.bills.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class UserApiConfiguration {

	@Bean
	public RegisterAccountToUserPort registerAccountToUserPort() {
		return (userId, account) -> null;
	}

	@Bean
	public FindEntityByIdPort<User, Id> findEntityByIdPort() {
		return id -> Objects.requireNonNull(UserRepository.cache.get(id));
	}

	@Bean
	public FindEntityByFilterPort<User, Id> findEntityByFilterPort() {
		return filter -> UserRepository.cache.values().stream().filter(filter).findAny().get();
	}

	@Bean
	public SaveOrUpdateEntityPort<User> saveOrUpdateEntityPort() {
		return entity -> {
			User user = ImmutableUser.copyOf(entity).withId(Id.NEW);
			UserRepository.cache.put(user.getId(), user);
			return UserRepository.cache.get(user.getId());
		};
	}

	@Bean
	public FindAllEntitiesPort<User, Id> findAllEntitiesPort() {
		return e -> Flux.<User>empty();
	}

	@Bean
	public FindEntityApiComponent<User, Id> findEntityApiComponent(FindEntityByIdPort<User, Id> findEntityByIdPort, FindEntityByFilterPort<User, Id> findEntityByFilterPort) {
		return ImmutableFindEntityApiComponent.of(findEntityByIdPort, findEntityByFilterPort);
	}

	@Bean
	public ExistsEntityApiComponent<User, Id> existsEntityApiComponent(FindEntityByIdPort<User, Id> findEntityByIdPort, FindEntityByFilterPort<User, Id> findEntityByFilterPort) {
		return ImmutableExistsEntityApiComponent.<User, Id>builder()
			.findEntityByFilterPort(findEntityByFilterPort)
			.findEntityByIdPort(findEntityByIdPort)
			.build();
	}

	@Bean
	public SaveOrUpdateEntityApiComponent<User, Id> saveOrUpdateEntityApiComponent(FindEntityByIdPort<User, Id> findEntityByIdPort, SaveOrUpdateEntityPort<User> saveOrUpdateEntityPort) {
		return ImmutableSaveOrUpdateEntityApiComponent.<User, Id>builder()
			.saveOrUpdateEntityPort(saveOrUpdateEntityPort)
			.findEntityByIdPort(findEntityByIdPort)
			.build();
	}

	@Bean
	public FindAllEntitiesApiComponent<User, Id> findAllEntitiesApiComponent(FindAllEntitiesPort<User, Id> findAllEntitiesPort) {
		return ImmutableFindAllEntitiesApiComponent.<User, Id>builder()
			.findAllEntitiesPort(findAllEntitiesPort)
			.build();
	}

	@Bean
	public RegisterAccountToUserComponent registerAccountToUserComponent(RegisterAccountToUserPort registerAccountToUserPort) {
		return ImmutableRegisterAccountToUserComponent.builder()
			.registerAccountToUserPort(registerAccountToUserPort)
			.build();
	}

	@Bean
	public UserApi userApi(
		FindEntityApiComponent<User, Id> findEntityApiComponent,
		ExistsEntityApiComponent<User, Id> existsEntityApiComponent,
		SaveOrUpdateEntityApiComponent<User, Id> saveOrUpdateEntityApiComponent,
		FindAllEntitiesApiComponent<User, Id> findAllEntitiesApiComponent,
		RegisterAccountToUserComponent registerAccountToUserComponent
	) {
		return new ImmutableUserApi(findEntityApiComponent, existsEntityApiComponent, saveOrUpdateEntityApiComponent, findAllEntitiesApiComponent, registerAccountToUserComponent);
	}

}

class UserRepository {
	public static final Map<Id, User> cache = new ConcurrentHashMap<>();
}