package com.spe.workwize.service.role;

import com.spe.workwize.bean.Role;

public interface RoleService {
    Role findByName(String name);
}
