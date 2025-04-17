package com.boic.testTask.news;

import com.boic.testTask.comments.CommentsJpa;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NewsJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @CreatedDate
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "last_edit_date")
    private LocalDateTime lastEditDate;

    @CreatedBy
    @Column(name = "inserted_by_id", updatable = false)
    private Long insertedById;

    @LastModifiedBy
    @Column(name = "updated_by_id")
    private Long updatedById;

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<CommentsJpa> comments = new ArrayList<>();
}
