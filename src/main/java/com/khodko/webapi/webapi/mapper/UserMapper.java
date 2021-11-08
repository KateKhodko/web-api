package com.khodko.webapi.webapi.mapper;

import com.khodko.webapi.webapi.dto.ProfileDto;
import com.khodko.webapi.webapi.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<Profile, ProfileDto> {
}
