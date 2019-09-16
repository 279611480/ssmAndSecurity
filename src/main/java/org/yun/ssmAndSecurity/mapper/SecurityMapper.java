package org.yun.ssmAndSecurity.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yun.ssmAndSecurity.entity.SecurityUser;

/**
 * @ClassName SecurityMapper
 * @Description
 * @Autor 落笔丶
 * @Date 2019/9/14 16:17
 * @Tel 279611480@qq.com
 */
@Repository
public interface SecurityMapper {

    @Select("SELECT * FROM security_user WHERE username = #{username}")
    public SecurityUser FindByUsername(@Param("username") String username);
}
