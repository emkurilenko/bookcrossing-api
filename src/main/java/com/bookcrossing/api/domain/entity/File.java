package com.bookcrossing.api.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file", schema = "bookcrossing_service")
public class File extends BaseNamedEntity<UUID> {

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotNull
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] content;
}
