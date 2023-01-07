package com.ggar.bills.rest.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
@Value.Style(allParameters = true, optionalAcceptNullable = true)
@JsonSerialize(as = ImmutableCreateAccountRequest.class)
@JsonDeserialize(as = ImmutableCreateAccountRequest.class)
public interface CreateAccountRequest {

	String name();
	UUID paymentId();
	UUID userId();

}
