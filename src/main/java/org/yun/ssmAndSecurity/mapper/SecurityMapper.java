package org.yun.ssmAndSecurity.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yun.ssmAndSecurity.entity.SecurityPermission;
import org.yun.ssmAndSecurity.entity.SecurityRole;
import org.yun.ssmAndSecurity.entity.SecurityUser;

import java.util.List;

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
    public SecurityUser findByUsername(@Param("username") String username);

    @Select("SELECT r.* \n" +
            "FROM security_user u , security_role r , security_user_role ur \n" +
            "WHERE  u.id = #{id} \n" +
            "AND ur.user_id = u.id \n" +
            "AND ur.role_id = r.id ")
    List<SecurityRole> findByUserId(@Param("id") String id);

    @Select("SELECT p.* \n" +
            "FROM security_role r , security_permission p , security_permission_role pr \n" +
            "WHERE r.id = #{id} \n" +
            "AND pr.role_id = r.id \n" +
            "AND pr.permission_id = p.id \n")
    List<SecurityPermission> findByRoleId(@Param("id") String id);


    @Select("SELECT * " +
            "FROM security_permission ")
    List<SecurityPermission> findAllPermission();

}
