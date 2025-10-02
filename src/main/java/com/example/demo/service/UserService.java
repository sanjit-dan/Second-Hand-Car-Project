package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private   ModelMapper modelMapper;
    private JWTService jwtService;
    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserDto convertEntityToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public User convertDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto saveUser(UserDto userDto) {
        Optional<User> opUser = userRepository.findByUserName(userDto.getUserName());
        if(opUser.isPresent()){
            throw new AllReadyExistsException("this user is all-ready exists");
        }
        Optional<User>opEmail =userRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()){
            throw new  AllReadyExistsException("this email is all-ready exists");
        }

        String hashpw = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
        User user= convertDtoToEntity(userDto);
            user.setPassword(hashpw);
        userRepository.save(user);
         UserDto dto =convertEntityToDto(user);
        return dto;
    }

    public String verifyLogin(LoginDto loginDto){
        Optional<User> opUSer = userRepository.findByUserName(loginDto.getUserName());
        if(opUSer.isPresent()){
            User user = opUSer.get();

           // throw new AllReadyExistsException("this password not match try again");
            if( BCrypt.checkpw(loginDto.getPassword(),user.getPassword())){
                 return jwtService.generateToken(user.getUserName());
            }
        }
        return null;

    }
}
