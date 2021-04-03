package com.bookcrossing.api.service.impl;

import static com.bookcrossing.api.validator.ValidatorType.CHANGE_PASSWORD;
import static com.bookcrossing.api.validator.ValidatorType.UNIQUE_USER;
import static com.bookcrossing.api.validator.ValidatorType.USER_DATA;

import com.bookcrossing.api.domain.dto.user.ChangePasswordRequest;
import com.bookcrossing.api.domain.dto.user.UserDTO;
import com.bookcrossing.api.domain.entity.User;
import com.bookcrossing.api.domain.mapper.BaseMapper;
import com.bookcrossing.api.domain.repository.UserRepository;
import com.bookcrossing.api.service.UserService;
import com.bookcrossing.api.utils.AuthUtils;
import com.bookcrossing.api.validator.MiddlewareValidator;
import com.bookcrossing.api.validator.ValidatorType;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends DefaultBaseService<UserDTO, User, Long> implements UserService,
        UserDetailsService {

    private static final String BASE_USER_ROLE = "USER";

    private final BaseMapper<UserDTO, User> mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;
    private final MiddlewareValidator middlewareValidator;

    public UserServiceImpl(
            BaseMapper<UserDTO, User> mapper,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthUtils authUtils,
            MiddlewareValidator middlewareValidator) {
        super(mapper, userRepository);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authUtils = authUtils;
        this.middlewareValidator = middlewareValidator;
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
        middlewareValidator.validate(user, List.of(USER_DATA, UNIQUE_USER));

        user.setRole(BASE_USER_ROLE);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        UserDTO persisted = super.persist(user);
        persisted.setPassword(null);
        return persisted;
    }

    @Override
    public UserDTO getCurrentUser() {
        return authUtils.getCurrentUser();
    }

    @Override
    public UserDTO changePassword(ChangePasswordRequest changePasswordRequest) {
        middlewareValidator.validate(changePasswordRequest, List.of(CHANGE_PASSWORD));

        UserDTO user = findById(changePasswordRequest.getUserId());
        String newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPassword(newPassword);

        return super.persist(user);
    }

    @Override
    public UserDTO persist(UserDTO value) {
        middlewareValidator.validate(value, List.of(USER_DATA));

        UserDTO currentUser = getCurrentUser();
        currentUser.setFirstName(value.getFirstName());
        currentUser.setLastName(value.getLastName());
        currentUser.setHobbies(value.getHobbies());
        currentUser.setEmail(value.getEmail());
        currentUser.setPhoto(value.getPhoto());
        return super.persist(currentUser);
    }
}
