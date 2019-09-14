package org.yun.ssmAndSecurity.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.yun.ssmAndSecurity.mapper.prodiver.UserProvider;
import org.yun.ssmAndSecurity.vo.UserVO;

import java.util.List;

@Repository
public interface UserMapper  {

    /*    @Select("SELECT factory_name \n" +
                "FROM factory \n" +
                "WHERE is_delete = 0 \n" +
                "AND factory_name LIKE '%${name}%' \n" +
                "ORDER BY updated_time DESC ; ") */
//    @SelectProvider(method = "listPage",type = FactoryProvider.class)
//    List<FactoryVO> listPage(@Param("dto") FactoryDTO dto);



    //    @Select("select * from user")
    @SelectProvider(method = "findAll" , type = UserProvider.class)
    List<UserVO> findAll();
}