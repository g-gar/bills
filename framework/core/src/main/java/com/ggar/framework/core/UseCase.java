package com.ggar.framework.core;

public interface UseCase<T extends UseCaseArguments, R> {

	default Result<R> execute(T arguments) {
		throw new RuntimeException("Not implemented");
	}

}