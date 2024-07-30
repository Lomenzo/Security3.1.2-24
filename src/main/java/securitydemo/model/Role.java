package securitydemo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Slf4j
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

    @Column(name = "rolename")
    private String roleName;

    @ManyToMany (mappedBy = "roleList", fetch = FetchType.EAGER)
    private List<User> userList;

    public Role() {
    }

    public Role(String roleName, List<User> userList) {
        this.roleName = roleName;
        this.userList = userList;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

//    @Override
//    public String toString() {
//        return "ROLE_" +
//                roleName;
//    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
