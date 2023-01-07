package com.ggar.bills.core.usecase;

import com.ggar.bills.core.port.FindAllEntitiesPort;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allParameters = true)
public interface FindAllEntitiesApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<FindAllEntitiesApiComponent.FindAllEntitiesUseCaseArguments<T>, Flux<T>> {

	FindAllEntitiesPort<T, ID> findAllEntitiesPort();

	@Override
	default Result<Flux<T>> execute(FindAllEntitiesUseCaseArguments<T> findAllEntitiesUseCaseArguments) {
		return Result.of(this.findAllEntitiesPort()::execute, findAllEntitiesUseCaseArguments.filter());
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
