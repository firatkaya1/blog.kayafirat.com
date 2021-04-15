package com.kayafirat.blog.service;


import com.kayafirat.blog.dto.AuthenticateRequest;
import com.kayafirat.blog.dto.Register;
import com.kayafirat.blog.dto.UserProfileDTO;
import com.kayafirat.blog.entity.NotificationPermission;
import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.entity.UserPermission;
import com.kayafirat.blog.entity.UserProfile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

    User getUser(Long id);

    UserProfileDTO getUserProfile(Long id);

    void saveUser(Register register);

    String login(AuthenticateRequest authenticateRequest) throws Exception;

    User updateUser(User user);

    void deleteUser(Long id);

    UserPermission getUserPermissions(Long id);

    UserPermission setUserPermissions(UserPermission userPermission,Long id);

    UserProfileDTO setUserProfile(UserProfileDTO userProfile, Long id);

    NotificationPermission getUserNotificationPermissions(Long id);

    void updateUserImage(MultipartFile multipartFile,Long id);
}
