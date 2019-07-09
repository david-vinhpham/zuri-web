package com.ocha.boc.services.impl;

import com.ocha.boc.base.AbstractResponse;
import com.ocha.boc.dto.UserDTO;
import com.ocha.boc.entity.User;
import com.ocha.boc.enums.UserType;
import com.ocha.boc.repository.UserRepository;
import com.ocha.boc.request.UserLoginRequest;
import com.ocha.boc.request.UserUpdateRequest;
import com.ocha.boc.response.UserResponse;
import com.ocha.boc.util.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse newUser(UserLoginRequest request) {
        UserResponse response = new UserResponse();
        response.setSuccess(Boolean.FALSE);
        response.setMessage(CommonConstants.CREATE_NEW_USER_FAIL);
        try {
            User user = userRepository.findUserByPhone(request.getPhone());
            if (user == null) {
                user = new User();
                user.setPhone(request.getPhone());
                user.setActive(Boolean.TRUE);
                user.setCreatedDate(Instant.now().toString());
                user.setRole(UserType.USER);
                userRepository.save(user);
                response.setSuccess(Boolean.TRUE);
                response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                response.setObjectId(user.getId());
                UserDTO userDTO = new UserDTO(user);
                response.setObject(userDTO);
            } else {
                response.setMessage(CommonConstants.USER_EXISTED);
            }
        } catch (Exception e) {
            log.error("Error when newUser: ", e);
        }
        return response;
    }

    public UserResponse updateUserInformation(UserUpdateRequest request) {
        UserResponse response = new UserResponse();
        response.setMessage(CommonConstants.UPDATE_USER_FAIL);
        response.setSuccess(Boolean.FALSE);
        try {
            User user = checkUserExisted(request.getUserId());
            if (user != null) {
                if (StringUtils.isNotEmpty(request.getEmail())) {
                    user.setEmail(request.getEmail());
                }
                if (StringUtils.isNotEmpty(request.getFirstName())) {
                    user.setFirstName(request.getFirstName());
                }
                if (StringUtils.isNotEmpty(request.getLastName())) {
                    user.setLastName(request.getLastName());
                }
                if (StringUtils.isNotEmpty(request.getPhoto())) {
                    user.setPhoto(request.getPhoto());
                }
                if (StringUtils.isNotEmpty(request.getRole().toString())) {
                    user.setRole(request.getRole());
                }
                user.setLastModifiedDate(Instant.now().toString());
                userRepository.save(user);
                response.setSuccess(Boolean.TRUE);
                response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                response.setObject(new UserDTO(user));
            } else {
                response.setMessage(CommonConstants.USER_IS_NULL);
            }
        } catch (Exception e) {
            log.error("Error when updateUserInformation: ", e);
        }
        return response;
    }

    public UserResponse getAllUser() {
        UserResponse response = new UserResponse();
        response.setSuccess(Boolean.FALSE);
        response.setMessage(CommonConstants.GET_ALL_USER_FAIL);
        try {
            List<User> users = userRepository.getListUserActiveIsTrue();
            if (CollectionUtils.isNotEmpty(users)) {
                List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
                for (User user : users) {
                    listUserDTO.add(new UserDTO(user));
                }
                response.setSuccess(Boolean.TRUE);
                response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                response.setObjects(listUserDTO);
                response.setTotalResultCount((long) listUserDTO.size());
            }
        } catch (Exception e) {
            log.error("Error when getAllUser: ", e);
        }
        return response;
    }

    public UserResponse getUserById(String userId) {
        UserResponse response = new UserResponse();
        response.setMessage(CommonConstants.USER_IS_NULL);
        response.setSuccess(Boolean.FALSE);
        try {
            if (StringUtils.isNotEmpty(userId)) {
                User user = userRepository.findUserById(userId);
                if (user != null) {
                    response.setSuccess(Boolean.TRUE);
                    response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                    response.setObject(new UserDTO(user));
                }
            }
        } catch (Exception e) {
            log.error("Error when getUserById: ", e);
        }
        return response;
    }

    public AbstractResponse deActiveUser(String userId) {
        AbstractResponse response = new AbstractResponse();
        response.setMessage(CommonConstants.USER_IS_NULL);
        response.setSuccess(Boolean.FALSE);
        try {
            if (StringUtils.isNotEmpty(userId)) {
                User user = userRepository.findUserById(userId);
                if (user != null) {
                    user.setLastModifiedDate(Instant.now().toString());
                    user.setActive(Boolean.FALSE);
                    userRepository.save(user);
                    response.setSuccess(Boolean.TRUE);
                    response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                }
            }
        } catch (Exception e) {
            log.error("Error when deActiveUser: ", e);
        }
        return response;
    }

    private User checkUserExisted(String id) {
        User user = null;
        try {
            user = userRepository.findUserById(id);
        } catch (Exception e) {
            log.error("Error when checkUserExisted: ", e);
        }
        return user;
    }

    public UserResponse activeUser(String userId) {
        UserResponse response = new UserResponse();
        response.setSuccess(Boolean.FALSE);
        response.setMessage(CommonConstants.USER_IS_NULL);
        try {
            User user = userRepository.findUserById(userId);
            if (user != null) {
                user.setLastModifiedDate(Instant.now().toString());
                user.setActive(Boolean.TRUE);
                userRepository.save(user);
                response.setSuccess(Boolean.TRUE);
                response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                response.setObject(new UserDTO(user));
            }
        } catch (Exception e) {
            log.error("Error when activeUser: ", e);
        }
        return response;
    }
}
