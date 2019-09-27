package org.yun.ssmAndSecurity.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yun.ssmAndSecurity.entity.SecurityPermission;
import org.yun.ssmAndSecurity.entity.SecurityRole;
import org.yun.ssmAndSecurity.entity.SecurityUser;

import java.util.List;

public interface SecurityService {
    @RequestMapping("/security/findByUsername")
    SecurityUser findByUsername(@RequestParam("username") String username);

    @RequestMapping("/security/findByUserId")
    List<SecurityRole> findByUserId(@RequestParam("id") String id);

    @RequestMapping("/security/findByRoleId")
    List<SecurityPermission> findByRoleId(@RequestParam("id") String id);

    @RequestMapping("/security/findAllPermission")
    List<SecurityPermission> findAllPermission();

}
