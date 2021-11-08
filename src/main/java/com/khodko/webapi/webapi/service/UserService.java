package com.khodko.webapi.webapi.service;

import com.khodko.webapi.webapi.dto.ChangePasswordRequest;
import com.khodko.webapi.webapi.dto.ProfileDto;
import com.khodko.webapi.webapi.entity.Profile;
import com.khodko.webapi.webapi.entity.Role;
import com.khodko.webapi.webapi.mapper.BaseMapper;
import com.khodko.webapi.webapi.repository.RoleRepository;
import com.khodko.webapi.webapi.repository.UserRepository;
import com.khodko.webapi.webapi.utils.AuthUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService, BaseService<ProfileDto> {

    private static final String BASE_USER_ROLE = "USER";

    private final BaseMapper<Profile, ProfileDto> mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthUtils authUtils, BaseMapper<Profile, ProfileDto> mapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUtils = authUtils;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
    }

    public ProfileDto findByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(mapper::entityToDto)
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user.not.found"));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(
                BASE_USER_ROLE));

        return new org.springframework.security.core.userdetails.User(
                profile.getLogin(),
                profile.getPass(),
                authorities);
    }

    public ProfileDto registerNewUser(ProfileDto user) {
        Role role = roleRepository.findByName(BASE_USER_ROLE);
        user.setRole(role);
        String password = user.getPass();
        user.setPass(passwordEncoder.encode(password));
        ProfileDto persisted = mapper.entityToDto(userRepository.save(mapper.dtoToEntity(user)));
        persisted.setPass(null);
        return persisted;
    }

    public ProfileDto getCurrentUser() {
        return authUtils.getCurrentUser();
    }

    public ProfileDto changePassword(ChangePasswordRequest changePasswordRequest) {
        ProfileDto user = findById(changePasswordRequest.getUserId());
        String newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPass(newPassword);
        Profile savedProfile = userRepository.save(mapper.dtoToEntity(user));
        return mapper.entityToDto(savedProfile);
    }

    @Override
    public List<ProfileDto> findAll() {
        return null;
    }

    @Override
    public ProfileDto create(ProfileDto entityDto) {
        Profile savedProfile = userRepository.save(mapper.dtoToEntity(entityDto));
        return mapper.entityToDto(savedProfile);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public ProfileDto findById(Long id) {
        return null;
    }
}