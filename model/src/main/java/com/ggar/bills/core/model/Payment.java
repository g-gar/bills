package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.core.model.field.PaymentTitle;
import com.ggar.bills.core.model.field.PaymentType;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface Payment extends BaseEntity<Id> {

	@Override
	@Value.Default
	default Id getId() {
		return ImmutableId.builder().build();
	}

	PaymentTitle title();

	PaymentType type();
}