package com.boic.testTask.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Formula;

@MappedSuperclass
@Getter
@Setter
@Accessors
public abstract class AuditUpdatedInformation {
    @Formula("(SELECT au.person_id FROM accountants.users au WHERE au.username = updated_by LIMIT 1)")
    private Long updatedByPersonId;
}
