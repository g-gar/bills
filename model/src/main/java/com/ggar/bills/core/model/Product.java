package com.ggar.bills.core.model;

import com.ggar.bills.core.model.field.Id;
import com.ggar.bills.core.model.field.ImmutableId;
import com.ggar.framework.core.BaseEntity;
import org.immutables.value.Value;

import java.time.OffsetDateTime;

@Value.Immutable
@Value.Style(allParameters = true)
public interface Product extends BaseEntity<Id> {

	@Override
	@Value.Default
	default Id getId() {
		return ImmutableId.builder().build();
	}

	String name();
	Double units();
	Double quantity();
	Double costPerUnit();
	OffsetDateTime date();

}