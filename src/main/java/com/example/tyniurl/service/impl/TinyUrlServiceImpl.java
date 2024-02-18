package com.example.tyniurl.service.impl;

import com.example.tyniurl.dto.TinyCreateDto;
import com.example.tyniurl.dto.TinyDto;
import com.example.tyniurl.exception.NotFoundException;
import com.example.tyniurl.mappers.TinyMapper;
import com.example.tyniurl.models.TinyUrl;
import com.example.tyniurl.repositories.TinyUrlRepository;
import com.example.tyniurl.service.TinyUrlService;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TinyUrlServiceImpl implements TinyUrlService {

    private final TinyUrlRepository tinyUrlRepository;

    @Override
    @Cacheable(value = "url")
    @Transactional
    public String getUrl(String hash) {
        return tinyUrlRepository.findUrl(hash).orElseThrow(() -> new NotFoundException("Url не найден"));
    }

    @Override
    public TinyDto createHash(TinyCreateDto url) {

        log.info("Create hash from url - {}", url);

        Optional<TinyUrl> findTiny = tinyUrlRepository.findHash(url.getUrl());
        if(findTiny.isPresent()){
            return TinyMapper.INSTANSE.tinyUrlToTinyDto(findTiny.get());
        }

        TinyUrl tinyUrl = TinyMapper.INSTANSE.tinyCreateDtoToTiny(url);
        TinyUrl savedTiny = tinyUrlRepository.save(tinyUrl);

        savedTiny.setHashUrl(
                Hashing
                        .crc32()
                        .hashLong(savedTiny.getId())
                        .toString());

        return TinyMapper.INSTANSE.tinyUrlToTinyDto(tinyUrlRepository.save(savedTiny));
    }

    @Override
    @CacheEvict(value = "url")
    @Transactional
    public void delete(String hash) {
        log.info("Delete with hash - {}", hash);
        tinyUrlRepository.deleteByHashUrl(hash);
    }

    @Override
    public List<TinyDto> getAll(Integer size, Integer page) {
        return tinyUrlRepository.findAll(PageRequest.of(page, size)).stream().map(TinyMapper.INSTANSE::tinyUrlToTinyDto).toList();
    }
}
