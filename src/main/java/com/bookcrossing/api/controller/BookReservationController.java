package com.bookcrossing.api.controller;


import static com.bookcrossing.api.controller.UrlConstants.ID_MAPPING;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class BookReservationController {

    @PatchMapping(ID_MAPPING)
    public ResponseEntity<?> reserve() {
        return ResponseEntity.ok().build();
    }
}

