package com.ggar.framework.core;

import com.ggar.framework.core.function.BiFunction;
import com.ggar.framework.core.function.Function;
import com.ggar.framework.core.function.Supplier;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public final class Result<T> {

	public static <T> Result<T> empty() {
		return Result.<T>builder().build();
	}

	public static <T> Result<T> failed(Exception exception) {
		return Result.<T>builder()
			.exception(Optional.ofNullable(exception))
			.build();
	}

	public static <T> Result<T> failed(Supplier<Exception> supplier) {
		return Result.<T>failed(supplier.get().get());
	}

	@Builder.Default
	Optional<T> object = Optional.empty();
	@Builder.Default
	Optional<Exception> exception = Optional.empty();

	public Boolean isPresent() {
		return this.object.isPresent();
	}

	public Boolean hasFailed() {
		return this.exception.isPresent();
	}

	public T get() {
		return this.object.orElse(null);
	}

	public static <R> Result<R> of(Supplier<R> supplier) {
		return supplier.get();
	}

	public static <T, R> Result<R> of(Function<T, R> fn, T t) {
		return fn.apply(t);
	}

	public static <T0, T1, R> Result<R> of(BiFunction<T0, T1, R> fn, T0 t0, T1 t1) {
		return fn.apply(t0, t1);
	}

	public <R> Result<R> map(Function<T,R> fn) {
		return this.isPresent()
			? Result.of(fn, this.get())
			: Result.<R>failed(NullPointerException::new);
	}
}