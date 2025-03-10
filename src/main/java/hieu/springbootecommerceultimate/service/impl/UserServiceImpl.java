package hieu.springbootecommerceultimate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.springbootecommerceultimate.entity.RoleEntity;
import hieu.springbootecommerceultimate.entity.UserEntity;
import hieu.springbootecommerceultimate.exception.UserAlreadyExistException;
import hieu.springbootecommerceultimate.exception.UserNotFoundException;
import hieu.springbootecommerceultimate.repository.RoleRepository;
import hieu.springbootecommerceultimate.repository.UserRepository;
import hieu.springbootecommerceultimate.request.CreateUserRequest;
import hieu.springbootecommerceultimate.request.UpdateUserRequest;
import hieu.springbootecommerceultimate.response.ForgetPasswordResponse;
import hieu.springbootecommerceultimate.response.ResetPasswordResponse;
import hieu.springbootecommerceultimate.response.UserPagingResponse;
import hieu.springbootecommerceultimate.response.UserResponse;
import hieu.springbootecommerceultimate.service.EmailService;
import hieu.springbootecommerceultimate.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
//    private final EmailService emailService;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        Optional<UserEntity> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }

        UserEntity entity = userRepository.save(convertRequestToEntity(request));
        return convertEntityToResponse(entity);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        UserEntity userEntity = findUserById(userId);
        return convertEntityToResponse(userEntity);
    }

    @Override
    public UserPagingResponse getUsers(String email, int page, int size) {

        email = "%" + email + "%";
        int offset = page * size;
        int limit = size;

        int count = userRepository.countByEmail(email);
        List<UserEntity> users = userRepository.findByEmail(email, limit, offset);

        UserPagingResponse response = UserPagingResponse.builder()
                .count(count)
                .users(convertEntitiesToResponses(users))
                .pageSize(size)
                .totalPages(count/size)
                .currentPage(page)
                .build();
        return response;
    }

    @Override
    public UserResponse activeUser(Long userId) {
        UserEntity userEntity = findUserById(userId);
        if (userEntity.isEnabled()) {
            throw new UserAlreadyExistException("User already activated");
        }
        userEntity.setEnabled(Boolean.TRUE);
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity savedUser = userRepository.save(userEntity);
        return convertEntityToResponse(savedUser);
    }

    @Override
    public ForgetPasswordResponse forgetPassword(String email) {
        UserEntity userEntity = findUserByEmail(email);

        // Generate new random password and save it to database
        String token = generateToken();
        userEntity.setToken(token);
        userRepository.save(userEntity);

        return ForgetPasswordResponse.builder()
                .resetToken(token)
                .email(email)
                .build();
    }

    @Override
    public ResetPasswordResponse resetPassword(String token) {
        UserEntity userEntity = userRepository.findByToken(token).orElseThrow(() -> new UserNotFoundException("User token not found"));
        String defaultPassword = "123a123@A";
        userEntity.setPassword(passwordEncoder.encode(defaultPassword));
        userEntity.setToken(null);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
        return ResetPasswordResponse.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        UserEntity entity = findUserById(request.getId());

        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setPhoto(request.getPhoto());
        entity.setEnabled(Boolean.TRUE);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setUpdatedAt(LocalDateTime.now());

        List<RoleEntity> roles = request.getRoles().stream().map(roleId -> roleRepository.findById(roleId).get()).toList();
        entity.setRoles(roles);
        UserEntity updatedUser = userRepository.save(entity);

        return convertEntityToResponse(updatedUser);
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User email = " + email + " not found"));
    }

    private UserEntity findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User id = " + userId + " not found"));
    }

    private UserEntity convertRequestToEntity(CreateUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setPhoto(request.getPhoto());
        entity.setEnabled(Boolean.FALSE);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setCreatedAt(LocalDateTime.now());

        List<RoleEntity> roles = request.getRoles().stream().map(roleId -> roleRepository.findById(roleId).get()).toList();
        entity.setRoles(roles);
        return entity;
    }

    private UserResponse convertEntityToResponse(UserEntity entity) {
        return objectMapper.convertValue(entity, UserResponse.class);
    }

    private List<UserResponse> convertEntitiesToResponses(List<UserEntity> entities) {
        return entities.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }
}
