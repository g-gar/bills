package com.ggar.bills.core.port;

import com.ggar.framework.core.Result;

public interface SaveOrUpdateEntityPort<T> {

	T execute(T entity) throws Exception;

}