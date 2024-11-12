package com.kush.pos.entity.process;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResource {

    @Column(columnDefinition = "LONGBLOB")
    private Blob fileName;

    @Column(columnDefinition = "LONGBLOB")
    private Blob resourceUrl;

    @Column(columnDefinition = "LONGBLOB")
    private Blob directory;

    @Column(columnDefinition = "LONGBLOB")
    private Blob hash;
}
