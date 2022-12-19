package com.kozeljko.expensetracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.util.Strings;

@Entity(name = "user")
public class User {

    private Long id;
    private String username;
    private String password;
    private String rolesString;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolesString() {
        return rolesString;
    }

    public void setRolesString(String rolesString) {
        this.rolesString = rolesString;
    }

    @Transient
    public List<String> getRoles() {
        return Arrays.asList(getRolesString().split(","));
    }

    public void setRoles(List<String> roles) {
        setRolesString(Strings.join(roles, ','));
    }
}
