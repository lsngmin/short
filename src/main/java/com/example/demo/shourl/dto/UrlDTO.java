package com.example.demo.shourl.dto;

import com.example.demo.shourl.entity.UrlEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlDTO {
    private Long uid;
    private String ourl;
    private String surl;
    private LocalDateTime joinDate;
    private LocalDateTime expireDate;

    public UrlDTO(UrlEntity urlEntity) {
        this.uid = urlEntity.getUid();
        this.ourl = urlEntity.getOurl();
        this.surl = urlEntity.getSurl();
        this.joinDate = urlEntity.getJoinDate();
        this.expireDate = urlEntity.getExpireDate();
    }
    public UrlEntity toEntity() {
        return UrlEntity.builder()
                .uid(uid)
                .ourl(ourl)
                .surl(surl)
                .joinDate(joinDate)
                .expireDate(expireDate)
                .build();
    }
}
