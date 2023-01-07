package com.ggar.framework.core.function;

import com.ggar.framework.core.Result;

import java.util.Optional;

@FunctionalInterface
public interface Supplier<R> extends java.util.function.Supplier<Result<R>> {

	@Override
	default Result<R> get() {
		Result.ResultBuilder<R> result = Result.builder();
		try {
			result = result.object(Optional.ofNullable(this.getThrows()));
		} catch (Exception exception) {
			result = result.exception(Optional.of(exception));
		}
		return result.build();
	}

	<E extends Exception> R getThrows() throws Exception;
}