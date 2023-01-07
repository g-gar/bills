package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.bills.core.model.field.StoreLocation;
import com.ggar.bills.core.model.field.StoreName;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface Store extends BaseEntity<Id> {

	@Override
	@Value.Default
	default Id getId() {
		return ImmutableId.builder().build();
	}

	StoreName name();

	StoreLocation location();

	Company company();

}