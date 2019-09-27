package org.yun.ssmAndSecurity.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yun.ssmAndSecurity.entity.SecurityUser;
import org.yun.ssmAndSecurity.vo.UserVO;

import java.util.List;

public interface UserService {

    @RequestMapping("/user/login")
    List<UserVO> findAll();

/*
//条件查询
@RequestMapping("/factory/listPage")
PageInfo<FactoryVO> listPage(@RequestBody FactoryDTO dto);
*/
}
