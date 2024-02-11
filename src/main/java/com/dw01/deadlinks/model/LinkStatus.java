package com.dw01.deadlinks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkStatus {
    private String link;
    private int statusCode;
    private String statusMessage;
}