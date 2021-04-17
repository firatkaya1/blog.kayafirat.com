package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.AuthenticateRequest;
import com.kayafirat.blog.dto.Register;
import com.kayafirat.blog.dto.UserProfileDTO;
import com.kayafirat.blog.entity.*;
import com.kayafirat.blog.exception.custom.UserEmailAlreadyExistsException;
import com.kayafirat.blog.exception.custom.UserNotFoundException;
import com.kayafirat.blog.exception.custom.UsernameAlreadyExistsException;
import com.kayafirat.blog.repository.UserRepository;
import com.kayafirat.blog.service.UserService;

import com.kayafirat.blog.util.JwtUtil;
import com.kayafirat.blog.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired private Environment env;
    private final JwtUtil jwtUtil;

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    @Cacheable(cacheNames = "userProfile", key = "#id")
    public UserProfileDTO getUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException());
        UserProfileDTO userProfileDTO = new UserProfileDTO(user);
        return userProfileDTO;
    }

    @Override
    @CachePut(value = "userProfile", key = "#id",unless = "#result == null")
    public UserProfileDTO setUserProfile(UserProfileDTO userProfile,Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new NullPointerException());
        UserProfile _userProfile = user.getUserProfile();
        if(userProfile.getEmail()!=null)
            user.setEmail(userProfile.getEmail());
        if(userProfile.getUsername()!=null)
            user.setUsername(userProfile.getUsername());
        if(userProfile.getPhoto()!=null)
            user.setPhoto(userProfile.getPhoto());
        if(userProfile.getContactAddress()!=null)
            _userProfile.setContactAddress(userProfile.getContactAddress());
        if(userProfile.getBirthDate()!=null)
            _userProfile.setBirthDate(userProfile.getBirthDate());
        if(userProfile.getGithubAddress()!=null)
            _userProfile.setGithubAddress(userProfile.getGithubAddress());
        if(userProfile.getLinkedinAddress()!=null)
            _userProfile.setLinkedinAddress(userProfile.getLinkedinAddress());
        user.setUserProfile(_userProfile);
        return new UserProfileDTO(userRepository.save(user));
    }

    @Override
    public void saveUser(Register register) {
        User user = new User();
        UserPermission userPermission = new UserPermission();
        UserProfile userProfile = new UserProfile();
        NotificationPermission notificationPermission = new NotificationPermission();
        user.setUserPermission(userPermission);
        user.setUserProfile(userProfile);
        user.setNotificationPermission(notificationPermission);
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmailAddress());
        user.setPassword(register.getPassword());
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Bu kullanıcı adı alınmış.");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserEmailAlreadyExistsException("Bu e-posta adresi alınmış.");
        }
        

        userRepository.save(user);

    }

    @Override
    public String login(AuthenticateRequest authRequest) throws Exception {
        User user = userRepository.findByUsernameOrEmail(authRequest.getUsername(), authRequest.getUsername())
                .orElseThrow(() ->  new UserNotFoundException());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(user.getId()),user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password.", e);
        } 
        final UserDetails userDetails = loadUserByUsername(String.valueOf(user.getId()));
        return jwtUtil.generateToken(userDetails,user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = {"user","userProfile","userPermissions","notificationPermissions","userDetail"}, key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "userPermissions", key = "#id")
    public UserPermission getUserPermissions(Long id) {
        return userRepository.findById(id).get().getUserPermission();
    }

    @Override
    @CachePut(value = {"userPermissions"}, key = "#id")
    public UserPermission setUserPermissions(UserPermission userPermission,Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NullPointerException());
        userPermission.setId(user.getUserPermission().getId());
        user.setUserPermission(userPermission);
        return userRepository.save(user).getUserPermission();
    }

    @Override
    @Cacheable(cacheNames = "notificationPermissions", key = "#id")
    public NotificationPermission getUserNotificationPermissions(Long id) {
        return userRepository.findById(id).get().getNotificationPermission();
    }

    @Override
    public void updateUserImage(MultipartFile multipartFile, Long id) {
        byte[] bytes;
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        try {
            String pathURI = env.getProperty("user.default.profile-photo") +  user.getId()+ "." + multipartFile.getOriginalFilename().split("\\.")[1];
            bytes = multipartFile.getBytes();
            Path path = Paths.get(pathURI);
            Files.write(path, bytes);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Cacheable(cacheNames = "userDetail", key = "#id")
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(()-> new UserNotFoundException());
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (Role role:user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), securityUtil.encode(user.getPassword()),authorities);
    }



}
