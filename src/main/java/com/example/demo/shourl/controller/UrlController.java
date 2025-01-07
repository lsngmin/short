package com.example.demo.shourl.controller;

import com.example.demo.shourl.dto.UrlDTO;
import com.example.demo.shourl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shourl")
@Log4j2
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    @GetMapping("")
    public String[] hello() {
        return new String[] {"hello, world!"};
    }
    @PostMapping("/convert")
    public String urlConvert(@RequestBody Map<String, String> urlInfo) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setOurl(urlInfo.get("url"));
        //변환 로직
        String uuid = UUID.randomUUID().toString().substring(0,6);
        urlDTO.setSurl(uuid);

        urlDTO.setExpireDate(LocalDateTime.now().plusHours(1));
        urlService.register(urlDTO);
        log.info("url convert start");
        return urlDTO.getSurl();
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable String uuid) {
        UrlDTO urlDTO = urlService.read(uuid);
        if (urlDTO.getExpireDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("URL is expired");//현재 시간 보다 만료기간이 전이면 만료되었다는 것이므로 연결하지 않음
        }
        String ourl = urlDTO.getOurl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(ourl))
                .build();
    }
}
