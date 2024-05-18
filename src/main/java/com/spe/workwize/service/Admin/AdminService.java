package com.spe.workwize.service.admin;

import com.spe.workwize.customModel.UserModel;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<UserModel> getAllManager();
    List<UserModel> getAllEmployee();
    Map<String,String> deleteUser(Map<String,String> param);
}
