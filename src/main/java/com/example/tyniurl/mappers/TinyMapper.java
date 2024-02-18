package com.example.tyniurl.mappers;

import com.example.tyniurl.dto.TinyCreateDto;
import com.example.tyniurl.dto.TinyDto;
import com.example.tyniurl.models.TinyUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TinyMapper {

    TinyMapper INSTANSE = Mappers.getMapper(TinyMapper.class);

    @Mapping(source = "url", target = "url")
    TinyUrl tinyCreateDtoToTiny(TinyCreateDto tinyCreateDto);

    @Mapping(source = "url", target = "url")
    @Mapping(source = "hashUrl", target = "hash")
    TinyDto tinyUrlToTinyDto(TinyUrl tinyUrl);
}
