package com.nitheesh.user_service.service;

import com.nitheesh.user_service.dto.UserRequestDto;
import com.nitheesh.user_service.dto.UserResponseDto;
import com.nitheesh.user_service.entity.User;
import com.nitheesh.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());

        userRepository.save(user);

        return mapToDto(user);
    }

    public List<UserResponseDto> getALlUsers() {
        List<User> allUsers= userRepository.findAll();
        ArrayList<UserResponseDto> userResponseDtoArrayList = new ArrayList<>();
        for(User user: allUsers){
            userResponseDtoArrayList.add(mapToDto(user));
        }

        return userResponseDtoArrayList;
    }

    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No user found with id : "+id));

        return mapToDto(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public UserResponseDto updateUser(UserRequestDto userRequestDto,String  id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("No user found "));

        if(userRequestDto.getName()!=null){
            user.setName(userRequestDto.getName());
        }
        if(userRequestDto.getEmail()!=null){
            user.setEmail(userRequestDto.getEmail());
        }

        userRepository.save(user);

        return mapToDto(user);
    }
}
