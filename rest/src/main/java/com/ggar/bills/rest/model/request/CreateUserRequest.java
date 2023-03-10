package com.ggar.bills.rest.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true, optionalAcceptNullable = true)
@JsonSerialize(as = ImmutableCreateUserRequest.class)
@JsonDeserialize(as = ImmutableCreateUserRequest.class)
public interface CreateUserRequest {

	String username();
	String password();

}