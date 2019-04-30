package com.infobip.web.urlshortener.controllers;


import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.infobip.web.urlshortener.domain.User;
import com.infobip.web.urlshortener.services.UserService;
import com.infobip.web.urlshortener.utilities.MappingValueUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    static final String BASE_URL = "/api/v1/shortener/user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public MappingJacksonValue registerUser(@RequestBody User user) throws Exception {
        if (user.getAccountId() == null || user.getAccountId().equals("")) {
            throw new Exception("Account id must not be null or empty string!");
        }
        User tmpUser = userService.registerUser(user);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "description");
        if (tmpUser.getSuccess()) {
            filter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "description", "password");
        }
        return MappingValueUtility.mapValue("UserFilter", filter, tmpUser);
    }
}
