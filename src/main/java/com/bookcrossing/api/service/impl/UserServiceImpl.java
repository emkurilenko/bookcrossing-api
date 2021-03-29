package com.bookcrossing.api.service.impl;

import com.bookcrossing.api.config.dispatcher.Dispatcher;
import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.domain.entity.User;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.UserRepository;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.utils.AuthUtils;
import com.bookcrossing.api.validator.Validator;
import com.bookcrossing.api.validator.ValidatorType;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends DefaultBaseService<UserDTO, User, Long> implements UserService,
        UserDetailsService {

    private static final String BASE_USER_ROLE = "USER";

    private final BaseMapper<UserDTO, User> mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;
    private final Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher;

    public UserServiceImpl(
            BaseMapper<UserDTO, User> mapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthUtils authUtils,
            Dispatcher<ValidatorType, Validator<Object>> validatorDispatcher) {
        super(mapper, userRepository);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUtils = authUtils;
        this.validatorDispatcher = validatorDispatcher;
    }

    @Override
    public UserDTO findByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(mapper::mapToDTO)
                .orElse(null);
    }

    @Override
    public boolean existUserByLogin(String login) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user.not.found"));
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(
                BASE_USER_ROLE));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                authorities);
    }

    @Override
    public UserDTO registerNewUser(UserDTO user) {
        validatorDispatcher.getByName(ValidatorType.UNIQUE_USER)
                .validate(user);
        user.setRole(BASE_USER_ROLE);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        UserDTO persisted = this.persist(user);
        persisted.setPassword(null);
        return persisted;
    }

    @Override
    public UserDTO getCurrentUser() {
        return authUtils.getCurrentUser();
    }
}
