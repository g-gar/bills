package com.ggar.framework.core.function;

import com.ggar.framework.core.Result;

import java.util.Optional;

@FunctionalInterface
public interface BiFunction<T0, T1, R> extends java.util.function.BiFunction<T0, T1, Result<R>> {

	@Override
	default Result<R> apply(T0 t0, T1 t1) {
		Result.ResultBuilder<R> result = Result.builder();
		try {
			result = result.object(Optional.ofNullable(this.applyThrows(t0, t1)));
		} catch (Exception exception) {
			result = result.exception(Optional.of(exception));
		}
		return result.build();
	}

	<E extends Exception> R applyThrows(T0 t0, T1 t1) throws Exception;
}