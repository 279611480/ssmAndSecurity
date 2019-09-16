package org.yun.ssmAndSecurity.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @ClassName SecurityPermission
 * @Description SpringSecurity权限表
 * @Autor 落笔丶
 * @Date 2019/9/16 21:30
 * @Tel 279611480@qq.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "security_permission")
public class SecurityPermission {

    @Id
    private String id;
    private String name;
    private String description;
    private String url;
    private String pid;
//    @ManyToMany(mappedBy = "permissions")
    private List<SecurityRole> roles;
}
