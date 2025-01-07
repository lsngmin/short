package com.example.demo.shourl.service;

import com.example.demo.shourl.dto.UrlDTO;
import com.example.demo.shourl.entity.UrlEntity;
import com.example.demo.shourl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlDTO register(UrlDTO urlDTO) {
        UrlEntity urlEntity = urlDTO.toEntity();
        urlRepository.save(urlEntity);
        return new UrlDTO(urlEntity);
    }
    public UrlDTO read(String uuid) {
        Optional<UrlDTO> rst = urlRepository.getDTO(uuid);
        UrlDTO urlDTO = rst.orElseThrow();
        return urlDTO;
    }
}
