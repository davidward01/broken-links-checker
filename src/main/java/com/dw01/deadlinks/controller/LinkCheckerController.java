package com.dw01.deadlinks.controller;

import com.dw01.deadlinks.dto.LinkCheckRequest;
import com.dw01.deadlinks.model.LinkStatus;
import com.dw01.deadlinks.service.LinkCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LinkCheckerController {

    @Autowired
    private LinkCheckerService linkCheckerService;

    @PostMapping("/check")
    public ResponseEntity<List<LinkStatus>> checkLinks(@RequestBody LinkCheckRequest request) {
        try {
            List<LinkStatus> result = linkCheckerService.checkLinks(request.getUrl());
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

