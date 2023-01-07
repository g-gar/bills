package com.ggar.bills.core.usecase;

import com.ggar.bills.core.port.FindEntityByIdPort;
import com.ggar.bills.core.port.SaveOrUpdateEntityPort;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.Objects;
import java.util.Optional;

@Value.Immutable
@Value.Style(allMandatoryParameters = true)
public interface SaveOrUpdateEntityApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<SaveOrUpdateEntityApiComponent.SaveOrUpdateEntityUseCaseParameters<T>, T> {

	FindEntityByIdPort<T, ID> findEntityByIdPort();
	SaveOrUpdateEntityPort<T> saveOrUpdateEntityPort();

	@Value.Immutable
	@Value.Style(optionalAcceptNullable = true)
	interface SaveOrUpdateEntityUseCaseParameters<T> extends UseCaseArguments {
		Optional<T> entity();
	}

	@Override
	default Result<T> execute(SaveOrUpdateEntityUseCaseParameters<T> arguments) {
		Result<T> result = null;
		try {
			Result<T> temp = Result.of(this.findEntityByIdPort()::execute, Objects.requireNonNull(arguments.entity().get()).getId());
			if (temp.hasFailed() || temp.isPresent()) {
				result = Result.of(this.saveOrUpdateEntityPort()::execute, arguments.entity().get());
			} else {
//				TODO: update
				int a = 0;
			}
		} catch (Exception exception) {
			result = Result.<T>builder()
				.exception(Optional.of(exception))
				.build();
		}
		return result;
	}

}
