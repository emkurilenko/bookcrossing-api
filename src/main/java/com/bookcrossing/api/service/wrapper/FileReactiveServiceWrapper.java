package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.FileDTO;
import com.bookcrossing.api.domain.entity.File;
import com.bookcrossing.api.service.BaseService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileReactiveServiceWrapper extends BaseReactiveBaseServiceWrapper<FileDTO, File, UUID> {

    @Autowired
    public FileReactiveServiceWrapper(BaseService<FileDTO, File, UUID> service) {
        super(service);
    }
}
