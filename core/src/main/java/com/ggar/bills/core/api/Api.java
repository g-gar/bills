package com.ggar.bills.core.api;

import com.ggar.framework.core.BaseEntity;
import com.ggar.framework.core.Result;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public interface Api<T extends BaseEntity<ID>, ID> {

	Result<T> find(ID id);
	Result<T> find(Predicate<T> filter);
	Result<Boolean> exists(ID id);
	Result<Boolean> exists(Predicate<T> filter);
	Result<T> saveOrUpdate(T entity);
	Result<Flux<T>> findAll();
	Result<Flux<T>> findAll(Predicate<T> filter);

}