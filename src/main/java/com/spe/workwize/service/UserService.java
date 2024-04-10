package com.spe.workwize.service;


import com.spe.workwize.customModel.UserModel;
import com.spe.workwize.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User generateToken(Map<String, String> payload);

    UserModel saveUser(Map<String, String> payload);

    User findOne(String username);

    List<User> findAll();
}
