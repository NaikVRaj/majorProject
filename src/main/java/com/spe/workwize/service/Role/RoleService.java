package com.spe.workwize.service.Role;

import com.spe.workwize.models.Role;

public interface RoleService {
    Role findByName(String name);
}