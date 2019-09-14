package org.yun.ssmAndSecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.yun.ssmAndSecurity.mapper.UserMapper;
import org.yun.ssmAndSecurity.service.UserService;
import org.yun.ssmAndSecurity.vo.UserVO;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Autor 拾笔丶
 * @Date 2019/9/7 22:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=true)
    @Qualifier("userMapper")
    UserMapper userMapper;
    //此处不是  修改  插入 删除  所以无需加上  事物注解    @Transactional
    @Override
    public List<UserVO> findAll() {
        return userMapper.findAll();
    }

/*
    @Override
    public PageInfo<FactoryVO> listPage(@RequestBody FactoryDTO dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<FactoryVO> list = factoryMapper.listPage(dto);
        return new PageInfo<>(list);
    }
*/
}
