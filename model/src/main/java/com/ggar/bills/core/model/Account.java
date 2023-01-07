package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.AccountName;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

import java.util.HashMap;
import java.util.Map;

@Value.Immutable
public interface Account extends BaseEntity<Id> {

	public static final Account UNDEFINED = ImmutableAccount.builder().build();

	@Override
	@Value.Default
	default Id getId() {
		return ImmutableId.builder().build();
	}

	AccountName name();

	Payment payment();

	@Value.Default
	default Map<Id, Bill> bills() {
		return new HashMap<>();
	}
}
