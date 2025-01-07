package com.example.demo.shourl.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_url", indexes = @Index(columnList = "uid"))
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Builder
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String ourl;
    private String surl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime joinDate;

    private LocalDateTime expireDate;
}
