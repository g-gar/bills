package com.ggar.bills.core.usecase;

import com.ggar.bills.core.api.AbstractApi;
import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

import java.util.function.Predicate;

@Value.Immutable
@Value.Style(allParameters = true)
public interface FindEntityApiComponent<T extends BaseEntity<ID>, ID> extends UseCase<FindEntityApiComponent.FindEntityUseCaseArguments<ID>, T> {

	AbstractApi<T, ID> api();

	@Override
	default Result<T> execute(FindEntityUseCaseArguments<ID> arguments) {
		return this.api().find(arguments.id());
	}

	@Value.Immutable
	@Value.Style(optionalAcceptNullable = true, allParameters = true)
	interface FindEntityUseCaseArguments<ID> extends UseCaseArguments {
		ID id();
	}
}
