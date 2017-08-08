package com.registration.entities;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="user_roles")
@Data
public class UserRole {
    private int id;
    private int version;
    private String username;
    private String userRole;

    private Date created;
    private Date modified;


    public UserRole(String username, String userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public UserRole() {

    }

    @Id
    @GeneratedValue
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    @Size(min = 1)
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    @CreationTimestamp
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    @UpdateTimestamp
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}