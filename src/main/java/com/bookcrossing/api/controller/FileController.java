package com.bookcrossing.api.controller;

import static com.bookcrossing.api.controller.UrlConstants.FETCH_FILE_MAPPING;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.bookcrossing.api.domain.dto.BaseEntityDTO;
import com.bookcrossing.api.domain.dto.FileDTO;
import com.bookcrossing.api.service.wrapper.ReactiveBaseServiceWrapper;
import com.bookcrossing.api.utils.FileUtils;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstants.FILE_MAPPING)
public class FileController {

    private final ReactiveBaseServiceWrapper<FileDTO, UUID> fileBaseService;
    private final FileUtils fileUtils;

    @Autowired
    public FileController(
            final ReactiveBaseServiceWrapper<FileDTO, UUID> fileBaseService,
            final FileUtils fileUtils) {
        this.fileBaseService = fileBaseService;
        this.fileUtils = fileUtils;
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public Mono<UUID> uploadFile(@RequestPart("file") Mono<FilePart> file) {
        return fileUtils.convert(file)
                .flatMap(fileBaseService::persist)
                .map(BaseEntityDTO::getId);
    }

    @GetMapping(FETCH_FILE_MAPPING)
    public Mono<ResponseEntity<byte[]>> fetchFile(
            @PathVariable UUID id,
            @RequestParam(required = false) boolean needDownload) {
        final String contentDisposition = needDownload ? "attachment;" : "inline;";
        return fileBaseService.findById(id)
                .map(item ->
                        ResponseEntity.ok()
                                .header(
                                        HttpHeaders.CONTENT_DISPOSITION,
                                        contentDisposition + "filename=" + item.getName())
                                .header(HttpHeaders.CONTENT_TYPE, item.getContentType())
                                .body(item.getContent()));
    }

}
