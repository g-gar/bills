package com.ggar.bills.rest.controller;

import com.ggar.bills.rest.endpoint.user.CreateUserEndpoint;
import com.ggar.bills.rest.endpoint.user.FindUserEndpoint;
import org.immutables.value.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
@Value.Immutable
@Value.Style(allParameters = true, passAnnotations = {RequestMapping.class}, of = "new")
public interface UserController extends CreateUserEndpoint, FindUserEndpoint {

}
