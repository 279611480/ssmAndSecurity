package org.yun.ssmAndSecurity.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName SecurityUser
 * @Description SpringSecurity的用户表
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
@Table(name = "security_user")
public class SecurityUser   {

    @Id
    private String id;
    private String username;
    private String password;
    private Timestamp createdTime;
    private Timestamp updatedTime;//new Timestamp(new Date().getTime());


//    //多对多 一个用户有很多个角色    一个角色亦是对应多个用户
//    //此处的配置表明user和role的多对多关系由user维护(此处是因为Role实体里面  有标明mappedBy = "roles" 表示外键关系由roles管理  )
//    @ManyToMany(fetch = FetchType.EAGER)//懒加载
//    @JoinTable(name = "security_user_role", joinColumns={@JoinColumn(name ="user_id",referencedColumnName = "id")},
//                inverseJoinColumns = {@JoinColumn( name = "role_id",referencedColumnName = "id")}
//                //name是中间表名，joinColumn中的name是对应的外键名，referencedColumnName在原先表中的主键名
//                //inverseJoinColumns中@JoinColumn里面的name是指：多 的一方在中间表的外键名，referencedCloumnName是：在原先表的主键名
//                //一对多   多对多    多的一方管理外键 关系
//    )
    private List<SecurityRole> roles;
}
