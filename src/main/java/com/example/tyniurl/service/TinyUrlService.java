package com.example.tyniurl.service;

import com.example.tyniurl.dto.TinyCreateDto;
import com.example.tyniurl.dto.TinyDto;

import java.util.List;

public interface TinyUrlService {

    String getUrl(String hash);

    TinyDto createHash(TinyCreateDto url);

    void delete(String hash);

    List<TinyDto> getAll(Integer size, Integer page);
}
