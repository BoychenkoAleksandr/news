package com.boic.testTask.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"updatedAt", "updatedBy"},
        allowGetters = true
)
@Getter
@Setter
@Accessors
public abstract class Updatable implements Serializable {

    @LastModifiedDate
    @Column(name = "creation_date")
    private Date updatedAt;

    @LastModifiedDate
    @Column(name = "creation_date")
    private Date creationAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    @Transactional
    public void prePersist() {
        this.beforeSave();
    }

    @PreUpdate
    @Transactional
    public void preUpdate() {
        this.beforeSave();
    }

    protected void beforePersist() {
        this.updatedAt = Date.from(Instant.now());
        this.updatedBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName)
                .orElse("SYSTEM");
    }

    protected void beforeSave() {
        this.updatedAt = Date.from(Instant.now());
        this.updatedBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName)
                .orElse("SYSTEM");
    }
}
