package io.pouch.service.mapper;

import io.pouch.controller.dto.response.UserGameResponse;
import io.pouch.controller.dto.update.UserGameUpdate;
import io.pouch.entities.UserGame;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = GameMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserGameMapper {

    @Mapping(source = "game", target = "gameResponse")
    UserGameResponse toResponse(UserGame userGame);

    @Mapping(target = "usergameId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "hoursPlayed", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UserGameUpdate update, @MappingTarget UserGame userGame);
}
