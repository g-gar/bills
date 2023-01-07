package com.ggar.bills.core.usecase;

import com.ggar.bills.core.port.FindEntityByFilterPort;
import com.ggar.bills.core.port.FindEntityByIdPort;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allParameters = true)
public interface FindEntityApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<FindEntityApiComponent.FindEntityUseCaseArguments<T, ID>, T> {

	FindEntityByIdPort<T, ID> findEntityByIdPort();
	FindEntityByFilterPort<T, ID> findEntityByFilterPort();

	@Override
	default Result<T> execute(FindEntityUseCaseArguments<T, ID> arguments) {
		return arguments.id().isPresent()
			? Result.of(this.findEntityByIdPort()::execute, arguments.id().get())
			: arguments.filter().isPresent()
				? Result.of(this.findEntityByFilterPort()::execute, arguments.filter().get())
				: Result.<T>failed(NullPointerException::new);
	}

	@Value.Immutable
	@Value.Style(optionalAcceptNullable = true, allParameters = true)
	interface FindEntityUseCaseArguments<T, ID> extends UseCaseArguments {
		Optional<ID> id();
		Optional<Predicate<T>> filter();
	}
}
