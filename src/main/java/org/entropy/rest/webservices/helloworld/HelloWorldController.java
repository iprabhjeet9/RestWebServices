package org.entropy.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path="/")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping(path="/hellobean")
	public HelloWorldBean hellobean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path="/hellobean/{name}")
	public HelloWorldBean hellobeanname(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s\n", name));
	}
}
