package io.pouch.service.mapper;

import io.pouch.controller.dto.request.UserGameRequest;
import io.pouch.entities.UserGame;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserGameMapper {

    UserGame toEntity(UserGameRequest request);
}
