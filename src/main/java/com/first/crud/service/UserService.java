package com.first.crud.service;

import com.first.crud.dto.UserDTO;
import com.first.crud.model.User;
import com.first.crud.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers(){
        List<User>userList = userRepo.findAll() ;
        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }
}
