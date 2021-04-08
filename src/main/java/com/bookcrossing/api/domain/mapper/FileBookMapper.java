package com.bookcrossing.api.domain.mapper;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.domain.entity.BookCrossingBaseEntity;
import com.bookcrossing.api.domain.entity.File;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileBookMapper {

    default List<UUID> mapIds(List<File> files) {
        return ofNullable(files)
                .orElseGet(List::of)
                .stream()
                .map(BookCrossingBaseEntity::getId)
                .collect(Collectors.toList());
    }

    default List<File> mapFiles(List<UUID> ids) {
        return ofNullable(ids)
                .orElseGet(List::of)
                .stream()
                .map(id -> {
                    File file = new File();
                    file.setId(id);
                    return file;
                })
                .collect(Collectors.toList());

    }

    default File mapFile(UUID id) {
        return ofNullable(id)
                .map(item -> {
                    File file = new File();
                    file.setId(item);
                    return file;
                })
                .orElse(null);

    }

    default UUID mapId(File file) {
        return ofNullable(file)
                .map(BookCrossingBaseEntity::getId)
                .orElse(null);
    }
}
