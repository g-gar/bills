package com.ggar.bills.core.port;

import com.ggar.framework.core.BaseEntity;
import reactor.core.publisher.Flux;

import java.util.function.Predicate;

public interface FindAllEntitiesPort<T extends BaseEntity<ID>, ID> {

	Flux<T> execute(Predicate<T> filter) throws Exception;

}
