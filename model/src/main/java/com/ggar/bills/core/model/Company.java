package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.CompanyName;
import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface Company extends BaseEntity<Id> {

	@Override
	@Value.Default
	default Id getId() {
		return ImmutableId.builder().build();
	}

	CompanyName name();
}