package com.ggar.bills.rest.endpoint.account;

import com.ggar.bills.core.api.HighLevelApi;
import com.ggar.bills.rest.mapper.UserMapper;

public interface CreateAccountEndpoint {

	HighLevelApi highLevelApi();
	UserMapper userMapper();

}
