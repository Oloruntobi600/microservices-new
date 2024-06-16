package com.programmingtechie.inventoryservice.controller;

import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j

public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        try {
            log.info("Received inventory check request for skuCode: {}", skuCode);
            return inventoryService.isInStock(skuCode);
        } catch (Exception e) {
            log.error("Error checking inventory", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error checking inventory");
        }
    }
}