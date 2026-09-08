package io.pouch.service.mapper;

import io.pouch.controller.dto.response.UserGameResponse;
import io.pouch.entities.UserGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = GameMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserGameMapper {

    @Mapping(source = "game", target = "gameResponse")
    UserGameResponse toResponse(UserGame userGame);
}
