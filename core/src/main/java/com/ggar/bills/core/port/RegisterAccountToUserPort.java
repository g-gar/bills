package com.ggar.bills.core.port;

import com.ggar.bills.core.model.Account;
import com.ggar.bills.core.model.User;

public interface RegisterAccountToUserPort {

	User execute(User user, Account account) throws Exception;

}