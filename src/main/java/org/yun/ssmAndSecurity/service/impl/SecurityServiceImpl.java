package org.yun.ssmAndSecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yun.ssmAndSecurity.entity.SecurityPermission;
import org.yun.ssmAndSecurity.entity.SecurityRole;
import org.yun.ssmAndSecurity.entity.SecurityUser;
import org.yun.ssmAndSecurity.mapper.SecurityMapper;
import org.yun.ssmAndSecurity.service.SecurityService;

import java.util.List;

/**
 * @ClassName SecurityServiceImpl
 * @Description TODO
 * @Autor 落笔丶
 * @Date 2019/9/14 16:17
 * @Tel 279611480@qq.com
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityMapper securityMapper;

    @Override
    public SecurityUser findByUsername(String username) {
        return securityMapper.findByUsername(username);
    }

    @Override
    public List<SecurityRole> findByUserId(String id) {
        return securityMapper.findByUserId(id);
    }

    @Override
    public List<SecurityPermission> findByRoleId(String id) {
        return securityMapper.findByRoleId(id);
    }

    @Override
    public List<SecurityPermission> findAllPermission() {
        return securityMapper.findAllPermission();
    }
}
