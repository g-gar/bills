package com.ggar.bills.core.api;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.field.Id;
import org.immutables.value.Value;

@Value.Immutable
public interface AccountApi extends AbstractApi<Account, Id> {

}
