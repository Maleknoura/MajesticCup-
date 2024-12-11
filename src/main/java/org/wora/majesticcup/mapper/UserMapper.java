package org.wora.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.entity.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AppUser toEntity(UserRequestDto createUtilisateurDto);
    ResponseUserDto toResponse(AppUser utilisateur);
}
