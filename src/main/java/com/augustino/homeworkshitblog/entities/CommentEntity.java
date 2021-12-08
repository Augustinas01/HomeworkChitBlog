package com.augustino.homeworkshitblog.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
    private UUID id;

    @ManyToOne
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String text;
}
