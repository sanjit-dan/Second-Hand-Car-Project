package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.payload.JWTTokenDto;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OTPService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserService userService;
    private OTPService otpService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, OTPService otpService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }
    // http://localhost:8080/api/v1/auth/admin-manager/signUp
    @PostMapping("/admin-manager/signUp")
    public ResponseEntity adminManager(@RequestBody UserDto userDto){
        userDto.setRole("ROLE_ADMIN");
        UserDto dto = userService.saveUser(userDto);
        return new ResponseEntity<>("Admin manager is insert successful", HttpStatus.CREATED);

    }

    // http://localhost:8080/api/v1/auth/content-manager/signUp
    @PostMapping("/content-manager/signUp")
    public ResponseEntity contentManager(@RequestBody UserDto uSerDto){
        uSerDto.setRole("ROLE_CONTENT");
        UserDto dto = userService.saveUser(uSerDto);
        return new ResponseEntity("Content manager insert successfull",HttpStatus.CREATED);
    }

    // http://localhost:8080/api/v1/auth/blog-manager/signUp
    @PostMapping("/blog-manager/signUp")
    public ResponseEntity blogManager(@RequestBody UserDto userDto){
        userDto.setRole("ROLE_BLOG");
       UserDto dto = userService.saveUser(userDto);
       return new ResponseEntity("Blog manager insert successful",HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/auth/signIn
    @PostMapping("/signIn")
    public ResponseEntity<?> userSignIn(@RequestBody LoginDto loginDto){
         String jwtToken =userService.verifyLogin(loginDto);
    if (jwtToken!=null) {
        JWTTokenDto tokenDto = new JWTTokenDto();
        tokenDto.setToken(jwtToken);
        tokenDto.setTokenType("JWT");
        return new ResponseEntity<>(tokenDto, HttpStatus.CREATED);
        }
    return new ResponseEntity<>("invalid token",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // http://localhost:8080/api/v1/auth/generate-otp
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String mobile){

            String otp = otpService.generateOtp(mobile);

            return new ResponseEntity<>("otp=" + otp + " mobileNumber=" + mobile, HttpStatus.OK);

    }

    // http://localhost:8080/api/v1/auth/validate-otp
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOTP(@RequestParam String mobile,@RequestParam String otp){
        otpService.validateOTP(mobile, otp);
        String token  =otpService.generateTokenAfterValidation(mobile);
        return  new ResponseEntity<>(token,HttpStatus.OK);

    }
}
