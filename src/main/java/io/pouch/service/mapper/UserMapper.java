package io.pouch.service.mapper;

import io.pouch.controller.dto.request.UserRequest;
import io.pouch.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "games", ignore = true)
    User toEntity(UserRequest request);
}
