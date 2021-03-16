package com.bookcrossing.api.domain.mapper;

import com.bookcrossing.api.domain.entity.BookCrossingBaseEntity;
import com.bookcrossing.api.domain.entity.File;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileBookMapper {

    default List<UUID> mapIds(List<File> files) {
        return files.stream()
                .map(BookCrossingBaseEntity::getId)
                .collect(Collectors.toList());
    }

    default List<File> mapFiles(List<UUID> ids) {
        return ids.stream()
                .map(id -> {
                    File file = new File();
                    file.setId(id);
                    return file;
                })
                .collect(Collectors.toList());

    }

    default File mapFile(UUID id) {
        File file = new File();
        file.setId(id);
        return file;
    }

    default UUID mapId(File file) {
        return file.getId();
    }
}
