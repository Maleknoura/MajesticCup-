package org.wora.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto createUtilisateurDto);
    ResponseUserDto toResponse(User utilisateur);
}
