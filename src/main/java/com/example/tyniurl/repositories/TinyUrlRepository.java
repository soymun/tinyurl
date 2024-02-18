package com.example.tyniurl.repositories;

import com.example.tyniurl.models.TinyUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {

    @Query("select tu.url from TinyUrl tu where tu.hashUrl=:hash")
    Optional<String> findUrl(@Param("hash") String hash);

    @Query("from TinyUrl tu where tu.url=:url")
    Optional<TinyUrl> findHash(@Param("url") String url);

    @Modifying
    void deleteByHashUrl(String hashUrl);
}
