package com.ggar.bills.rest.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.field.Id;
import org.immutables.value.Value;

import java.util.Map;
import java.util.UUID;

@Value.Immutable
@Value.Style(allParameters = true, optionalAcceptNullable = true)
@JsonSerialize(as = ImmutableCreateUserResponse.class)
@JsonDeserialize(as = ImmutableCreateUserResponse.class)
public interface CreateUserResponse {

	UUID userId();
	String username();
	Map<Id, Account> accounts();

}
