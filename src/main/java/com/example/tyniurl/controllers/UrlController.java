package com.example.tyniurl.controllers;

import com.example.tyniurl.dto.TinyCreateDto;
import com.example.tyniurl.dto.TinyDto;
import com.example.tyniurl.service.impl.TinyUrlServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UrlController {

    private final TinyUrlServiceImpl tinyUrlService;

    @GetMapping("/{hash}")
    public ResponseEntity<?> redirect(@PathVariable String hash){
        try {
            log.info("Redirect with hash - {}", hash);
            return ResponseEntity.status(301).header("Location", tinyUrlService.getUrl(hash)).build();
        } catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping
    public ResponseEntity<TinyDto> createUrl(@RequestBody TinyCreateDto tinyCreateDto){
        try {
            return ResponseEntity.ok(tinyUrlService.createHash(tinyCreateDto));
        } catch (Exception e){
            log.error("Error", e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<?> delete(@PathVariable String hash){
        try {
            tinyUrlService.delete(hash);
            return ResponseEntity.status(204).build();
        } catch (Exception e){
            log.error("Error", e);
            return ResponseEntity.status(500).build();
        }
    }
}
