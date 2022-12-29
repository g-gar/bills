package com.ggar.bills.core.api;

import com.ggar.bills.core.usecase.*;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import org.immutables.value.Value;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allMandatoryParameters = true)
public interface AbstractApi<T extends BaseEntity<ID>, ID> extends Api<T, ID> {

	FindEntityApiComponent<T, ID> findEntityApiComponent();
	ExistsEntityApiComponent<T, ID> existsEntityApiComponent();
	SaveOrUpdateEntityApiComponent<T, ID> saveOrUpdateEntityApiComponent();
	FindAllEntitiesApiComponent<T, ID> findAllEntitiesApiComponent();

	@Override
	default Result<T> find(ID id) {
		return this.findEntityApiComponent().execute(() -> id);
	}

	@Override
	default Result<Boolean> exists(ID id) {
		return this.existsEntityApiComponent().execute(ImmutableExistsEntityUseCaseParameters.<T, ID>builder()
			.id(id)
			.build()
		);
	}

	@Override
	default Result<Boolean> exists(Predicate<T> filter) {
		return this.existsEntityApiComponent().execute(ImmutableExistsEntityUseCaseParameters.<T, ID>builder()
			.filter(filter)
			.build()
		);
	}

	@Override
	default Result<T> saveOrUpdate(T entity) {
		return this.saveOrUpdateEntityApiComponent().execute(ImmutableSaveOrUpdateEntityUseCaseParameters.<T>builder()
			.entity(entity)
			.build()
		);
	}

	@Override
	default Result<Flux<T>> findAll() {
		return this.findAllEntitiesApiComponent().execute(null);
	}

	@Override
	default Result<Flux<T>> findAll(Predicate<T> filter) {
		return this.findAllEntitiesApiComponent().execute(null);
	}

}
