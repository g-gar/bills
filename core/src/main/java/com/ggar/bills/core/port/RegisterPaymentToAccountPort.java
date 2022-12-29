package com.ggar.bills.core.port;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.Payment;
import com.ggar.bills.core.model.field.Id;

import java.util.UUID;

public interface RegisterPaymentToAccountPort {

	Account execute(Id accountId, Payment payment);

}
