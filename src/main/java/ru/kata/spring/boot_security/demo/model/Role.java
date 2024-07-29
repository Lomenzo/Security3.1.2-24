package ru.kata.spring.boot_security.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Slf4j
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleID;

    @Column(name = "rolename")
    String roleName;

    @ManyToMany (mappedBy = "roleList"/*, fetch = FetchType.LAZY*/)
    List<User> userList;

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

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", userList=" + userList +
                '}';
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
