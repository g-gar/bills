package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Value.Immutable
@Value.Style(allParameters = true)
public interface User extends BaseEntity<Id> {

	public static final User UNDEFINED = ImmutableUser.builder().build();

	@Override
	@Value.Default
	@Value.Parameter(value = false)
	default Id getId() {
		return ImmutableId.builder().build();
	}

	@Value.Parameter
	String username();

	@Value.Parameter
	String hash();

	@Value.Default
	@Value.Parameter(value = false)
	default Map<Id, Account> accounts() {
		return new HashMap<>();
	}

}
