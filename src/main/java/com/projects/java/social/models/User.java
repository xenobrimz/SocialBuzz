package com.projects.java.social.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="username must be filled")
    private String name;

    @NotEmpty(message="email must be filled")
    private String email;

    @NotEmpty(message="password must be filled")
    @Size(min=5, message="password not long enough")
    private String password;

    @Transient
    @NotEmpty(message="password confirm must be filled")
    private String passwordConfirmation;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Buzz> buzzes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "zipped_buzzes", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "buzz_id")
    )
    private List<Buzz> zips;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "comments", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "buzz_id")
    )
    private List<Buzz> comments;

    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    

    public User() {
    }
    

    public User(Long id, String name, String email, String password, String passwordConfirmation, List<Buzz> buzzes, List<Buzz> zips, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.buzzes = buzzes;
        this.zips = zips;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return this.passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public List<Buzz> getBuzzes() {
        return this.buzzes;
    }

    public void setBuzzes(List<Buzz> buzzes) {
        this.buzzes = buzzes;
    }

    public List<Buzz> getZips() {
        return this.zips;
    }

    public void setZips(List<Buzz> zips) {
        this.zips = zips;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}