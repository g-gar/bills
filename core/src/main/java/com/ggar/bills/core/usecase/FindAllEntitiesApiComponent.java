package com.ggar.bills.core.usecase;

import com.ggar.bills.core.api.AbstractApi;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allParameters = true)
public interface FindAllEntitiesApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<FindAllEntitiesApiComponent.FindAllEntitiesUseCaseArguments<T>, Flux<T>> {

	AbstractApi<T, ID> api();

	@Override
	default Result<Flux<T>> execute(FindAllEntitiesUseCaseArguments<T> findAllEntitiesUseCaseArguments) {
		return this.api().findAll(findAllEntitiesUseCaseArguments.filter());
	}

	@Value.Immutable
	@Value.Style(allParameters = true, optionalAcceptNullable = true)
	interface FindAllEntitiesUseCaseArguments<T> extends UseCaseArguments {
		@Value.Default
		default Predicate<T> filter() {
			return e -> true;
		}
	}
}
