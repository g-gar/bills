package com.ggar.bills.rest.model;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface AccountDto {

	UUID accountId();
	String title();
	Double balance();
	UUID paymentId();

}
