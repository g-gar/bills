package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.AccountBalance;
import com.ggar.bills.core.model.field.AccountName;
import com.ggar.bills.core.model.field.Id;
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
		return Id.NEW;
	}

	@Value.Default
	default AccountName name() {
		return AccountName.UNDEFINED;
	}

	@Value.Default
	default AccountBalance balance() {
		return AccountBalance.UNDEFINED;
	}

	@Value.Default
	default Payment payment() {
		return Payment.UNDEFINED;
	}

	@Value.Default
	default Map<Id, Bill> bills() {
		return new HashMap<>();
	}
}
