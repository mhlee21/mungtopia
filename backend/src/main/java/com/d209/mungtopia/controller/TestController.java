package com.d209.mungtopia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/")
    private ResponseEntity<LocalDate> test() {
        LocalDate now = LocalDate.now();
        return new ResponseEntity<LocalDate>(now, HttpStatus.OK);
    }
}
