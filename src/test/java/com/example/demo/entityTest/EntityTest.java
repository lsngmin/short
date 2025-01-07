package com.example.demo.entityTest;

import com.example.demo.shourl.entity.UrlEntity;
import com.example.demo.shourl.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class EntityTest {
    @Autowired
    private UrlRepository urlRepository;

    @Test
    @Transactional
    public void testInsertUrl() {
        System.out.println("시작할게요~~~~~~~~~~~~~~~~~~~~");
        UrlEntity urlEntity = UrlEntity.builder()
                .ourl("https://~~~")
                .surl("https://~!!~")
                .expireDate(LocalDateTime.now())
                .build();
        urlRepository.save(urlEntity);
    }
}
