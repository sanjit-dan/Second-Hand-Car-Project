package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.OTPDetails;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

    private final UserRepository userRepository;
    private JWTService jwtService;
    public OTPService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public final Map<String, OTPDetails> otpStorage = new HashMap<>();

    private static final int OTP_EXPIRY_TIME=5;


    public String generateOtp(String mobile){
        String otp = String.format("%06d", new Random().nextInt(999999));
        OTPDetails otpDetails= new OTPDetails(otp,System.currentTimeMillis());

        otpStorage.put(mobile,otpDetails);

        return otp;
    }

    public boolean validateOTP(String mobileNumber,String otp){
        OTPDetails otpDetails = otpStorage.get(mobileNumber);
        if(otpDetails==null){
            return false;
        }
        long currentTime=System.currentTimeMillis();
        long otpTime= otpDetails.getTimeStamp();
        long timeDifference= TimeUnit.MILLISECONDS.toMinutes(currentTime-otpTime);

        if (timeDifference>OTP_EXPIRY_TIME){
            otpStorage.remove(mobileNumber);
            return false;
        }
        return otpDetails.getOtp().equals(otp);
    }

    public String generateTokenAfterValidation(String mobile){
        Optional<User> opMobile = userRepository.findByMobile(mobile);
        if(opMobile.isPresent()){
            User user = opMobile.get();
            return jwtService.generateToken(user.getUserName());
        }
        throw new ResourceNotFoundException("user not found this mobile number");
    }
}
