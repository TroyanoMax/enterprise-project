package com.crg.enterprisews.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditEntity implements Serializable {

    /**
     * SerialVersion.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The creation date.
     */
    @Column(name = "CREATED_DATE")
    @CreatedDate
    private Date createdDate;

    /**
     * The modification date.
     */
    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    private Date modifiedDate;

    /**
     * The creator of the record.
     */
    @Column(name = "CREATED_BY")
    @CreatedBy
    private String createdBy;

    /**
     * The last person that modified the record.
     */
    @Column(name = "MODIFIED_BY")
    @LastModifiedBy
    private String modifiedBy;

    /**
     * Flag that indicates if the record is active or not.
     */
    @Column(name = "IS_ACTIVE")
    private boolean isActive;

}
