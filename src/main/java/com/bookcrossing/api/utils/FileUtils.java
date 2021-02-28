package com.bookcrossing.api.utils;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.domain.dto.FileDTO;
import reactor.core.publisher.Mono;

import java.util.Objects;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {

    public Mono<FileDTO> convert(Mono<FilePart> file) {
        return file.flatMap(part ->
                part.content()
                        .reduce(DataBuffer::write)
                        .map(DataBuffer::asInputStream)
                        .flatMap(is -> Mono.fromCallable(is::readAllBytes))
                        .map(bytes ->
                                FileDTO.builder()
                                        .content(bytes)
                                        .name(part.filename())
                                        .contentType(convertContentType(part.headers()))
                                        .build()));
    }

    private String convertContentType(HttpHeaders headers) {
        return ofNullable(headers.getContentType())
                .map(MediaType::toString)
                .orElseThrow(() -> new NullPointerException("MediaType.is.null"));
    }
}
