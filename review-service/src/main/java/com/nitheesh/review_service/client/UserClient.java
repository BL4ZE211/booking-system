package com.nitheesh.review_service.client;

import com.nitheesh.review_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String id);
}
