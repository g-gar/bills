package com.ggar.bills.core.port;

import com.ggar.framework.core.BaseEntity;

import java.util.function.Predicate;

public interface FindEntityByFilterPort<T extends BaseEntity<ID>, ID> {

	T execute(Predicate<T> filter) throws Exception;

}