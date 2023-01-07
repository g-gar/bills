package com.ggar.bills.core.port;

import com.ggar.framework.core.BaseEntity;

public interface FindEntityByIdPort<T extends BaseEntity<ID>, ID> {

	T execute(ID id) throws Exception;

}