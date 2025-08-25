 package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.config.JwtProvider;
import com.nt.dto.AuthResponse;
import com.nt.dto.LoginRequest;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.User;
import com.nt.repository.UserRepository;
import com.nt.service.AuthService;
import com.nt.service.CustomUserServiceImplementation;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nt.config.JwtProvider;
//import com.nt.dto.AuthResponse;
//import com.nt.dto.LoginRequest;
//import com.nt.dto.UserResponseDTO;
//import com.nt.dto.UserSignupRequestDTO;
//import com.nt.model.User;
//import com.nt.repository.UserRepository;
//import com.nt.service.CustomUserServiceImplementation;
//
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthService authService;
	 @Autowired
//	    private AuthServices authServices;
//		@Autowired
		private UserRepository userRepository;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private CustomUserServiceImplementation customUserServiceImplementation;
	    @Autowired
	    private JwtProvider jwtProvider;
//	    @Autowired
//	    private PasswordResetTokenRepository tokenRepository;
//	    @Autowired
//	    private EmailService emailService;
	    
//	
//	@Autowired
//	private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private CustomUserServiceImplementation customUserServiceImplementation;
//    @Autowired
//    private JwtProvider jwtProvider;
//    
////    @PostMapping("/signup")
////    public ResponseEntity<User> createUserHandler(@RequestBody User user) throws Exception {
////    	
////    	String email=user.getEmail();
////    	String password=user.getPassword();
////    	String fullname=user.getFullName();
////    	String role=user.getRole();
////    	
////    	User isEmailExsist=userRepository.findByEmail(email);
////    	
////    	if(isEmailExsist!=null) {
////    		throw new Exception("Email is Already Used With Another Account");
////    	}
////    	//create new user
////    	User createUser=new User();
////    	createUser.setEmail(email);
////    	createUser.setFullName(fullname);
////    	createUser.setRole(role);
////    	createUser.setPassword(passwordEncoder.encode(password));
////    	
////    	User saveUser=userRepository.save(createUser);  
////    	 
////        
////        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
////    }
//    
//    @PostMapping("/signup")
//    public ResponseEntity<UserResponseDTO> createUserHandler(@RequestBody UserSignupRequestDTO request) throws Exception {
//
//        String email = request.getEmail();
//        String password = request.getPassword();
//        String fullname = request.getFullName();
//        String role = request.getRole();
//
//        User isEmailExist = userRepository.findByEmail(email);
//
//        if (isEmailExist != null) {
//            throw new Exception("Email is Already Used With Another Account");
//        }
//
//        // create new user entity
//        User createUser = new User();
//        createUser.setEmail(email);
//        createUser.setFullName(fullname);
//        createUser.setRole(role);
//        createUser.setPassword(passwordEncoder.encode(password));
//
//        User savedUser = userRepository.save(createUser);
//
//        // map to response DTO
//        UserResponseDTO response = new UserResponseDTO();
//        response.setId(savedUser.getId());
//        response.setEmail(savedUser.getEmail());
//        response.setFullName(savedUser.getFullName());
//        response.setRole(savedUser.getRole());
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    
//    @PostMapping("/signin")
//    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) throws Exception{
//    	
//    	String username=loginRequest.getEmail();
//    	String password=loginRequest.getPassword();
//
//    	System.out.println(username+"------"+password);
//    	
//    	Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//    	SecurityContextHolder.getContext().setAuthentication(authentication);
//    	
//    	String token=jwtProvider.generateToken(authentication);
//    	AuthResponse authResponse=new AuthResponse();
//    	
//    	authResponse.setMessage("Login Success");
//    	authResponse.setJwt(token);
//    	authResponse.setStatus(true);
//    	
//    	return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
//    }
//    private Authentication authenticate(String username, String password) {
//        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);
//
//        System.out.println("Sign in - userDetails: " + userDetails);
//
//        if (userDetails == null) {
//            System.out.println("Sign in - userDetails is null");
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            System.out.println("Sign in - password mismatch for user: " + username);
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    
	    
	    
	    
	    
	    
	    
	    
//	
//	@PostMapping("/signup")
//    public ResponseEntity<User> createUserHandler(@RequestBody User user) {
//        User registeredUser = authService.register(user);
//        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        
        
        

        @PostMapping("/signup")
        public ResponseEntity<UserResponseDTO> createUserHandler(@RequestBody User user) {
            User registeredUser = authService.register(user);

            // Map User entity to DTO
            UserResponseDTO dto = new UserResponseDTO(
                registeredUser.getFullName(),
                registeredUser.getEmail(),
                registeredUser.getPassword() // encoded password
            );

            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }

//    @PostMapping("/signin")
//    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
//        AuthResponse response = authService.login(loginRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
	    try {
	        AuthResponse response = authService.login(loginRequest);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (BadCredentialsException ex) {
	        throw ex; // let GlobalExceptionHandler catch it
	    }
	}

    
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
//        String result = authServices.forgotPassword(request.getEmail());
//        return ResponseEntity.ok(result);
//    }
//    
//    
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody ResetRequest request) {
//        String email = request.getEmail();
//        String otp = request.getOtp();
//        String newPassword = request.getNewPassword();
//
//        // ✅ Find the OTP in the DB
//        PasswordResetToken resetToken = tokenRepository.findByToken(otp).orElse(null);
//
//        if (resetToken == null || resetToken.getUser() == null) {
//            return ResponseEntity.badRequest().body("Invalid OTP.");
//        }
//
//        // ✅ Match the OTP with correct email
//        if (!resetToken.getUser().getEmail().equals(email)) {
//            return ResponseEntity.badRequest().body("OTP does not match this email.");
//        }
//
//        // ✅ Check expiry
//        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//            return ResponseEntity.badRequest().body("OTP has expired.");
//        }
//
//        // ✅ Reset password
//        User user = resetToken.getUser();
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        // ✅ Invalidate used token
//        tokenRepository.delete(resetToken);
//
//        return ResponseEntity.ok("✅ Password reset successfully! Please go to login page");
//    }
   }

 
 

 
 
 
 
 
 
