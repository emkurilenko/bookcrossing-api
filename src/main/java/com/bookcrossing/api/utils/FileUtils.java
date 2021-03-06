package com.bookcrossing.api.utils;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.domain.dto.FileDTO;
import com.google.common.io.ByteStreams;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

    public FileDTO convert(MultipartFile file) throws IOException {
        byte[] content = ByteStreams.toByteArray(file.getInputStream());

        return FileDTO.builder()
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .content(content)
                .build();
    }

    private String convertContentType(HttpHeaders headers) {
        return ofNullable(headers.getContentType())
                .map(MediaType::toString)
                .orElseThrow(() -> new NullPointerException("MediaType.is.null"));
    }
}
