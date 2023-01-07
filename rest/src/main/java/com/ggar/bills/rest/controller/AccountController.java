package com.ggar.bills.rest.controller;

import org.immutables.value.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user/{id}/account")
@Value.Immutable
@Value.Style(allParameters = true, passAnnotations = {RequestMapping.class}, of = "new")
public interface AccountController {
}
