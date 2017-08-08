package com.registration.entities;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="users")

public class User {

    private int id;
    private int version;
    private String username;
    private String email;
    private String password;
    private int enabled;
    private Date dob;
    private Date created;
    private Date modified;


    public User(String username, String email, String password, int enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;

    }

    public User() {
    }

    @Id
    @GeneratedValue
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotNull
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @NotNull
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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