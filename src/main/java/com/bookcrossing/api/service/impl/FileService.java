package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.domain.dto.FileDTO;
import com.bookcrossing.api.domain.entity.File;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.BaseCrudRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileService extends DefaultBaseService<FileDTO, File, UUID> {

    @Autowired
    public FileService(
            final BaseMapper<FileDTO, File> mapper,
            final BaseCrudRepository<File, UUID> repository) {
        super(mapper, repository);
    }
}
