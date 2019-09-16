package org.yun.ssmAndSecurity.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName SecurityUser
 * @Description SpringSecurity的用户角色中间表
 * @Autor 落笔丶
 * @Date 2019/9/14 15:11
 * @Tel 279611480@qq.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "security_user_role")
public class SecurityUserRole {

    @Id
    private String id;
    private String userId;
    private String roleId;
    private Timestamp createdTime;

}
