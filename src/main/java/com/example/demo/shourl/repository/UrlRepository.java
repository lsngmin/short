package com.example.demo.shourl.repository;

import com.example.demo.shourl.dto.UrlDTO;
import com.example.demo.shourl.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    @Query("select u from UrlEntity u where u.surl = :surl")
    Optional<UrlDTO> getDTO(@Param("surl") String surl);
}