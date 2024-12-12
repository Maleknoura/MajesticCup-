package org.wora.majesticcup.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.entity.AppUser;

public interface UserService {
    AppUser register(UserRequestDto userRequestDto);
    Page<ResponseUserDto> findAll(Pageable pageable);
    ResponseUserDto findById(String id);
    boolean delete(String id);
}
