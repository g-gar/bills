package com.ggar.bills.core.api;

import com.ggar.bills.core.model.Store;
import com.ggar.bills.core.model.field.Id;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface StoreApi extends AbstractApi<Store, Id> {
}