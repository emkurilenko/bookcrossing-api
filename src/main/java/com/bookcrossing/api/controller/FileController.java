package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.FETCH_FILE_MAPPING;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.dto.FileDTO;
import com.bookcrossing.api.domain.entity.File;
import com.bookcrossing.api.service.BaseService;
import com.bookcrossing.api.utils.FileUtils;
import lombok.SneakyThrows;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(UrlConstants.FILE_MAPPING)
public class FileController {

    private final BaseService<FileDTO, UUID> fileBaseService;
    private final FileUtils fileUtils;

    @Autowired
    public FileController(
            final BaseService<FileDTO, UUID> fileBaseService,
            final FileUtils fileUtils) {
        this.fileBaseService = fileBaseService;
        this.fileUtils = fileUtils;
    }

    @SneakyThrows
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public BaseEntityDTO<UUID> uploadFile(@RequestPart("file") MultipartFile file) {
        FileDTO value = fileUtils.convert(file);
//        value.setId(UUID.randomUUID());
        FileDTO persistFile = fileBaseService.persist(value);
        BaseEntityDTO<UUID> baseEntityDTO = new BaseEntityDTO<>();
        baseEntityDTO.setId(persistFile.getId());
        return baseEntityDTO;
    }

    @GetMapping(FETCH_FILE_MAPPING)
    public ResponseEntity<byte[]> fetchFile(
            @PathVariable UUID id,
            @RequestParam(required = false) boolean needDownload) {
        final String contentDisposition = needDownload ? "attachment;" : "inline;";
        FileDTO file = fileBaseService.findById(id);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        contentDisposition + "filename=" + file.getName())
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(file.getContent());
    }

}
