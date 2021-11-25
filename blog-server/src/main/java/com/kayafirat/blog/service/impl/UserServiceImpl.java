package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.AuthenticateRequest;
import com.kayafirat.blog.dto.Register;
import com.kayafirat.blog.dto.UserDTO;
import com.kayafirat.blog.dto.UserProfileDTO;
import com.kayafirat.blog.entity.*;
import com.kayafirat.blog.enums.Type;
import com.kayafirat.blog.exception.custom.SamePasswordException;
import com.kayafirat.blog.exception.custom.UserEmailAlreadyExistsException;
import com.kayafirat.blog.exception.custom.UserNotFoundException;
import com.kayafirat.blog.exception.custom.UsernameAlreadyExistsException;
import com.kayafirat.blog.repository.UserRepository;
import com.kayafirat.blog.service.MailService;
import com.kayafirat.blog.service.NotificationService;
import com.kayafirat.blog.service.UserService;
import com.kayafirat.blog.util.JwtUtil;
import com.kayafirat.blog.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final Environment env;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;
    private final MailService mailService;
    private final NotificationService notificationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SecurityUtil securityUtil,
                           Environment env, JwtUtil jwtUtil,
                           RestTemplateBuilder restTemplate,
                           MailService mailService, NotificationService notificationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
        this.env = env;
        this.jwtUtil = jwtUtil;
        this.mailService = mailService;
        this.notificationService = notificationService;
        this.restTemplate = restTemplate.build();
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
    public void saveUser(Register register)  {
        User user = new User();
        UserPermission userPermission = new UserPermission();
        UserProfile userProfile = new UserProfile();
        NotificationPermission notificationPermission = new NotificationPermission();
        MailPermission mailPermission = new MailPermission();
        mailPermission.setLoginAttempt(true);
        mailPermission.setPassChange(true);
        mailPermission.setPostNotification(true);
        userProfile.setRegisterDate(new Date());

        Role role = new Role();
        role.setId(1L);
        role.setRole("ROLE_USER");

        user.setRole(new HashSet<>(Collections.singletonList(role)));
        user.setMailPermission(mailPermission);
        user.setUserPermission(userPermission);
        user.setUserProfile(userProfile);
        user.setNotificationPermission(notificationPermission);
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmailAddress());
        user.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Bu kullanıcı adı alınmış.");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserEmailAlreadyExistsException("Bu e-posta adresi alınmış.");
        }
        // send verification message
        MailQueue mailQueue = new MailQueue();
        mailQueue.setMailType(Type.Verification);
        mailQueue.setEmailAddress(user.getEmail());
        mailService.save(mailQueue);
        User _user = userRepository.save(user);
        // add a notification
        Notification notification = new Notification();
        notification.setUser(_user);
        notification.setTitle("Aramıza hoşgeldin.");
        notification.setMessage("Kayıt olduğun için teşekkürler, şimdi rahatlıkla yazıları okuyabilir, yorum yapabilir ve beğenebilirsin. Sana iyi okumalar :)");
        notification.setLink(env.getProperty("base.link"));
        notificationService.addNotification(notification);
    }

    @Override
    public String login(AuthenticateRequest authRequest) throws Exception {
        User user = userRepository.findByUsernameOrEmail(authRequest.getUsername(), authRequest.getUsername())
                .orElseThrow(() ->  new UserNotFoundException());

        if (!bCryptPasswordEncoder.matches(authRequest.getPassword(), user.getPassword()) || user == null)
            throw new UserNotFoundException();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(user.getId()),user.getPassword()));

            if(user.getNotificationPermission().isLoginNotification()) {
                Notification notification = new Notification();
                notification.setUser(user);
                notification.setTitle("Giriş yapıldı.");
                notification.setMessage(new Date()+" tarihinde hesabına giriş yaptın.");
                notificationService.addNotification(notification);
            }

            if(user.getMailPermission().isLoginNotification()) {
                MailQueue mailQueue = new MailQueue();
                mailQueue.setEmailAddress(user.getEmail());
                mailQueue.setMailType(Type.LoginSuccess);
                mailService.save(mailQueue);
            }

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password.", e);
        } 
        final UserDetails userDetails = loadUserByUsername(String.valueOf(user.getId()));
        return jwtUtil.generateToken(userDetails,user);
    }

    private String loginByAuth(String email) throws Exception {
        User user = userRepository.findByUsernameOrEmail(email, email)
                .orElseThrow(() ->  new UserNotFoundException());

        if (user == null)
            throw new UserNotFoundException();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(user.getId()),user.getPassword()));

            if(user.getNotificationPermission().isLoginNotification()) {
                Notification notification = new Notification();
                notification.setUser(user);
                notification.setTitle("Giriş yapıldı.");
                notification.setMessage(new Date()+" tarihinde hesabına giriş yaptın.");
                notificationService.addNotification(notification);
            }

            if(user.getMailPermission().isLoginNotification()) {
                MailQueue mailQueue = new MailQueue();
                mailQueue.setEmailAddress(user.getEmail());
                mailQueue.setMailType(Type.LoginSuccess);
                mailService.save(mailQueue);
            }

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
    @Cacheable(cacheNames = "mailPermissions", key = "#id")
    public MailPermission getUserMailPermissions(Long id) {
        return userRepository.findById(id).get().getMailPermission();
    }

    @Override
    @CachePut(value = "mailPermissions", key = "#id")
    public MailPermission updateMailPermissions(MailPermission mailPermission, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        mailPermission.setId(user.getMailPermission().getId());
        user.setMailPermission(mailPermission);
        return userRepository.save(user).getMailPermission();
    }

    @Override
    public void updateUserImage(MultipartFile multipartFile, Long id) {
        byte[] bytes;
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        try {
            String pathURI = env.getProperty("user.default.profile-photo") +  user.getId()+ "." + multipartFile.getOriginalFilename().split("\\.")[1];
            bytes = multipartFile.getBytes();
            Path path = Paths.get(pathURI);
            user.setPhoto(pathURI);
            Files.write(path, bytes);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Cacheable(cacheNames = "userDetail", key = "#id")
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(UserNotFoundException::new);
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (Role role:user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), securityUtil.encode(user.getPassword()), user.isEnabled(),
                !user.isAccountExpired(),
                !user.isPasswordExpired(),
                !user.isAccountLocked(),authorities);
    }

    public String linkedinOauth(String code) throws Exception {
        String url = env.getProperty("oauth2.linkedin.root-uri")+"code="+code+"&client_id="+env.getProperty("oauth2.linkedin.client-id")+"&client_secret="+env.getProperty("oauth2.linkedin.client-secret")+"&redirect_uri="+env.getProperty("oauth2.linkedin.redirect-uri")+"&grant_type=authorization_code";
        String accessToken = restTemplate.postForEntity(url,null,Map.class).getBody().get("access_token").toString();
        url = "https://api.linkedin.com/v2/me";
        Map userMap = restTemplate.exchange(url, HttpMethod.GET,prepareHTTPEntity(accessToken), Map.class).getBody();
        url = "https://api.linkedin.com/v2/clientAwareMemberHandles?q=members&projection=(elements*(primary,type,handle~))";
        String val = restTemplate.exchange(url, HttpMethod.GET,prepareHTTPEntity(accessToken), Map.class).getBody().toString();
        String email = val.substring(val.indexOf("ss=")+3,val.lastIndexOf("}, "));
        String username = userMap.get("localizedFirstName").toString().toLowerCase() + userMap.get("localizedLastName").toString().toLowerCase() + ((int) (Math.random() * (10000) + 1000));
        return saveAuthUser("Github",email,username,createRandomPassword());
    }

    public String githubOauth(String code) throws Exception {
        String url = env.getProperty("oauth2.github.root-uri")+"access_token?client_id="+env.getProperty("oauth2.github.client-id")+"&client_secret="+env.getProperty("oauth2.github.client-secret")+"&code="+code;
        String accessToken =  restTemplate.postForEntity(url,null,String.class).getBody().substring(13,53);
        JSONObject jsonObject = new JSONObject(restTemplate.exchange(env.getProperty("oauth2.github.user-root-uri"), HttpMethod.GET,prepareHTTPEntity(accessToken), Map.class).getBody());
        return saveAuthUser("Github",jsonObject.get("email").toString(),jsonObject.get("login").toString(),createRandomPassword());
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            MailQueue mailQueue = new MailQueue();
            mailQueue.setEmailAddress(user.getEmail());
            mailQueue.setMailType(Type.PasswordChanged);
            mailService.save(mailQueue);
        }
    }

    @Override
    public void resetPassword(String password, String token) {
        String email = jwtUtil.extractUserEmail(token);
        User user = userRepository.findByEmail(email);
        if(user != null ) {
            if(!bCryptPasswordEncoder.matches(password,user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(password));
                // send notification
                Notification notification = new Notification();
                notification.setCreatedDate(new Date());
                notification.setMessage("Hey, Şifren "+notification.getCreatedDate()+" tarihinde değiştirildi. Eğer bunu sen yapmadıysan lütfen tıkla ve yeni bir şifre talep et!");
                notification.setTitle("Şifre Değişikliği");
                notification.setLink(env.getProperty("base.link")+"forgotpassword");
                notification.setUser(user);
                notificationService.addNotification(notification);
                // send mail
                MailQueue mailQueue = new MailQueue();
                mailQueue.setEmailAddress(user.getEmail());
                mailQueue.setMailType(Type.PasswordChangedSuccess);
                mailService.save(mailQueue);
                userRepository.save(user);
            } else {
                // old password and new password cannot be same !
                throw new SamePasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void unsubscribe(String token) {
        String email = jwtUtil.extractUserEmail(token);
        User user = userRepository.findByEmail(email);
        if(user != null){
            MailPermission mailPermission = user.getMailPermission();
            mailPermission.setPostNotification(false);
            user.setMailPermission(mailPermission);
            userRepository.save(user);

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setTitle("Mail üyeliğin sonlandırıldı");
            notification.setMessage("Hey, kısa süre önce mail üyeliğin sonlandırıldı. Eğer seni rahatsız ettiysek özür dileriz, tekrar görüşmek dileğiyle...");
            notificationService.addNotification(notification);
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public void verifyAccount(String token) {
        String email = jwtUtil.extractUserEmail(token);
        User user = userRepository.findByEmail(email);
        if(user != null){
            UserProfile userProfile = user.getUserProfile();
            userProfile.setAccountStatus(true);
            userRepository.save(user);

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setTitle("Hesabın onaylandı.");
            notification.setMessage("Hesabını onayladığın için teşekkürler "+user.getUsername()+", artık tamamen özgürsün.");
            notificationService.addNotification(notification);

            MailQueue mailQueue = new MailQueue();
            mailQueue.setEmailAddress(user.getEmail());
            mailQueue.setMailType(Type.VerificationSuccess);
            mailService.save(mailQueue);

        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * İki tip password oluşturuyorum. Birincisi databaseye kayıt etmem gereken format
     * ikincisi ise mevcut düzende doğrulamam gereken ham password.
     * Ham şifre kayıt edilmez.
     * @return
     */
    private String createRandomPassword() {
        String[] alphabet = {"a","b","c","d","e","f","g","h","l","A","B","C","D","E","F","G","L","1","2","3","4","5","6","7","8","9","0"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<=15;i++) {
            sb.append(alphabet[(int) (Math.random() * (25) + 0)]);
        }
        return sb.toString();
    }

    private String saveAuthUser(String socialAccount,String email, String username,String password) throws Exception {

        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);

            Notification notification = new Notification();
            notification.setUser(user);
            notification.setTitle(socialAccount+" hesabın aracılığıyla giriş yapıldı");
            notification.setMessage(new Date()+" tarihinde "+socialAccount+" hesabını kullanarak giriş yaptın.");
            notificationService.addNotification(notification);

            return loginByAuth(user.getEmail());
        }else {
            User user = new User();
            UserPermission userPermission = new UserPermission();
            NotificationPermission notificationPermission = new NotificationPermission();
            MailPermission mailPermission = new MailPermission();
            UserProfile userProfile = new UserProfile();
            userProfile.setAccountStatus(true);
            userProfile.setRegisterDate(new Date());
            user.setUserPermission(userPermission);
            user.setUserProfile(userProfile);
            user.setNotificationPermission(notificationPermission);
            user.setMailPermission(mailPermission);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            User _user = userRepository.save(user);

            Notification notification = new Notification();
            notification.setUser(_user);
            notification.setTitle(socialAccount+" hesabın aracılığıyla kayıt oldun.");
            notification.setMessage(new Date()+" tarihinde "+socialAccount+" hesabını kullanarak sisteme kayıt oldun.");
            notificationService.addNotification(notification);
        }
        return login(new AuthenticateRequest(email,password));
    }

    private HttpEntity<String> prepareHTTPEntity(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent","kayafirat.com");
        headers.setBearerAuth (accessToken);
        try {

        } catch (Exception e){

        }


        return new HttpEntity<>("parameters", headers);
    }

    /** Admin **/

    @Override
    public List<UserDTO> getUserList(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        users.forEach(user -> userDTOList.add(new UserDTO(user))  );
        return userDTOList;

    }

    @Override
    @CachePut(cacheNames = "user", key = "#_user.id")
    public User saveUser(User _user){

        if(!_user.getPassword().startsWith("$2a")){
            _user.setPassword(bCryptPasswordEncoder.encode(_user.getPassword()));
        }
        return userRepository.save(_user);
    }

}
