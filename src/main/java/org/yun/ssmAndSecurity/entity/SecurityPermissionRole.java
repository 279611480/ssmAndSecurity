package org.yun.ssmAndSecurity.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @ClassName SecurityUser
 * @Description SpringSecurity的权限角色表
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
@Table(name = "sercurity_permission_role")
public class SecurityPermissionRole {

    @Id
    private String id;

    private String roleId;

    private String permissionId;
}
