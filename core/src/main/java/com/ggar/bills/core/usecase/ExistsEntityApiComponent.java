package com.ggar.bills.core.usecase;

import com.ggar.bills.core.port.FindEntityByFilterPort;
import com.ggar.bills.core.port.FindEntityByIdPort;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allMandatoryParameters = true)
public interface ExistsEntityApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<ExistsEntityApiComponent.ExistsEntityUseCaseParameters<T, ID>, Boolean> {

	FindEntityByIdPort<T, ID> findEntityByIdPort();
	FindEntityByFilterPort<T> findEntityByFilterPort();

	@Override
	default Result<Boolean> execute(ExistsEntityUseCaseParameters<T, ID> arguments) {
		Result<Boolean> result = Result.<Boolean>builder()
			.exception(new NullPointerException())
			.build();

		if (arguments.id().isPresent()) {
			result = Result.from(this.findEntityByIdPort()::execute, arguments.id().get()).process(Objects::nonNull);
		}
		if (arguments.filter().isPresent()) {
			result = Result.from(this.findEntityByFilterPort()::execute, arguments.filter().get()).process(Objects::nonNull);
		}
		return result;
	}

	@Value.Immutable
	@Value.Style(optionalAcceptNullable = true, allParameters = true)
	interface ExistsEntityUseCaseParameters<T, ID> extends UseCaseArguments {
		Optional<ID> id();
		Optional<Predicate<T>> filter();
	}

}
