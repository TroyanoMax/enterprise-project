package com.crg.enterprisews.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Abstract audit dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditDTO implements Serializable {

    /**
     * SerialVersion.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The creation date.
     */
    @JsonProperty("createdDate")
    private Date createdDate;

    /**
     * The modification date.
     */
    @JsonProperty("modifiedDate")
    private Date modifiedDate;

    /**
     * The creator of the record.
     */
    @JsonProperty("createdBy")
    private String createdBy;

    /**
     * The last person that modified the record.
     */
    @JsonProperty("modifiedBy")
    private String modifiedBy;

    /**
     * Flag that indicates if the record is active or not.
     */
    @JsonProperty("isActive")
    private boolean isActive;

}
