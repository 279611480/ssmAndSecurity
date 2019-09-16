package org.yun.ssmAndSecurity.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SecurityRole
 * @Description SpringSecurity的角色表
 * @Autor 落笔丶
 * @Date 2019/9/14 15:16
 * @Tel 279611480@qq.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "security_role")
public class SecurityRole {

    @Id
    private String id;
    private String name;
    private Timestamp createdTime;
//    @ManyToMany(mappedBy = "roles")//(mappedBy = "roles")表明  外键关系由roles管理  见SecirotyUser实体类
    List<User> users = new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "security_permission_role",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
//                inverseJoinColumns = @JoinColumn(name = "persmission_id" , referencedColumnName = "id")
//    )
    private List<SecurityPermission> permissions = new ArrayList<>();






}
