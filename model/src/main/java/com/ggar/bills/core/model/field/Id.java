package com.ggar.bills.core.model.field;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
@Value.Style(allParameters = true)
public interface Id {

	public static final Id NEW = ImmutableId.builder().build();

	@Value.Default
	default UUID value() {
		return UUID.randomUUID();
	}

}
