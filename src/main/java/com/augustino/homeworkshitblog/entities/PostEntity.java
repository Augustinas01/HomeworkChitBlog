package com.augustino.homeworkshitblog.entities;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
    private UUID id;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    private UserEntity user;

    private Long likes;

    private String text;

    private String imageName;

    private Boolean isDeleted;

}
