package com.jms.boilerplate.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    public String getHelloWorld() {
        return "Response from api: Hello World Unrestricted";
    }

    public String getHelloWorldRestrictedAuthority() {
        return "Response from api: Hello World Restricted Authority";
    }

    public String getHelloWorldRestrictedAnyAuthority() { return "Response from api: Hello World Restricted Any Authority"; }

}
