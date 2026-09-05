package io.pouch.service.mapper;

import io.pouch.controller.dto.request.GameRequest;
import io.pouch.controller.dto.response.GameResponse;
import io.pouch.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GameMapper {

    @Mapping(target = "gameId", ignore = true)
    @Mapping(target = "releasedIn", ignore = true)
    @Mapping(target = "rating", ignore = true)
    Game toEntity(GameRequest request);

    GameRequest toRequest(Game game);

    GameResponse toResponse(Game game);
}
