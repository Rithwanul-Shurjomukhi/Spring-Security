package com.user.security.service;

import com.user.security.entity.User;
import com.user.security.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
