package com.ggar.bills.core.usecase;

import com.ggar.bills.core.model.Store;
import com.ggar.framework.core.UseCase;
import com.ggar.framework.core.UseCaseArguments;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface CreateStoreUseCase extends UseCase<CreateStoreUseCase.CreateStoreUseCaseArguments, Store> {

	@Value.Immutable
	@Value.Style(allParameters = true)
	interface CreateStoreUseCaseArguments extends UseCaseArguments {

	}

}