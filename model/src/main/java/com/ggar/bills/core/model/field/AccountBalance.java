package com.ggar.bills.core.model.field;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface AccountBalance extends Field<Double> {
}