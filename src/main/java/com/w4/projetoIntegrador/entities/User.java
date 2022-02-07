package com.w4.projetoIntegrador.entities;

import com.w4.projetoIntegrador.enums.ProfileTypes;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    public boolean isActive() {
        return enabled;
    }
    public void setActive(boolean active) {
        this.enabled = active;
    }
    @Id
    @Column(length = 20)
    private String username;
    @Column
    private String password;
    @Column
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private ProfileTypes profileType;


    public String getUser() {
        return username;
    }
    public void setUser(String user) {
        this.username = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileType() {
        return profileType.name();
    }
}
