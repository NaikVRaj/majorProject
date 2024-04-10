package com.spe.workwize.service;

import com.spe.workwize.models.Role;

public interface RoleService {
    Role findByName(String name);
}