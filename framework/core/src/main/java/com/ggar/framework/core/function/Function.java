package com.ggar.framework.core.function;

import com.ggar.framework.core.Result;

import java.util.Optional;

@FunctionalInterface
public interface Function<T, R> extends java.util.function.Function<T, Result<R>> {

	@Override
	default Result<R> apply(T t) {
		Result.ResultBuilder<R> result = Result.builder();
		try {
			result = result.object(Optional.ofNullable(this.applyThrows(t)));
		} catch (Exception exception) {
			result = result.exception(Optional.of(exception));
		}
		return result.build();
	}

	<E extends Exception> R applyThrows(T t) throws Exception;
}