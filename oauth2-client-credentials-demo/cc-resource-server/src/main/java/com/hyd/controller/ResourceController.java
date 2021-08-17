package com.hyd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rs")
public class ResourceController {
	@RequestMapping("hello")
	public String hello(){
		return "world";
	}
}
