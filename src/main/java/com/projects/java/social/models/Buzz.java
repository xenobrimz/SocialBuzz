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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="buzzes")
public class Buzz {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="name must be filled")
    @Size(max=3, message="This buzz is too large for this town")
    private String buzz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "zipped_buzzes", 
        joinColumns = @JoinColumn(name = "buzz_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> zippedBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "comments", 
        joinColumns = @JoinColumn(name = "buzz_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> comments;
    

    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;


    public Buzz() {
    }
    

    public Buzz(Long id, String buzz, User user, List<User> zippedBy, Date createdAt, Date updatedAt) {
        this.id = id;
        this.buzz = buzz;
        this.user = user;
        this.zippedBy = zippedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuzz() {
        return this.buzz;
    }

    public void setBuzz(String buzz) {
        this.buzz = buzz;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getZippedBy() {
        return this.zippedBy;
    }

    public void setZippedBy(List<User> zippedBy) {
        this.zippedBy = zippedBy;
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

    public Buzz id(Long id) {
        setId(id);
        return this;
    }

    public Buzz buzz(String buzz) {
        setBuzz(buzz);
        return this;
    }

    public Buzz user(User user) {
        setUser(user);
        return this;
    }

    public Buzz zippedBy(List<User> zippedBy) {
        setZippedBy(zippedBy);
        return this;
    }

    public Buzz createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Buzz updatedAt(Date updatedAt) {
        setUpdatedAt(updatedAt);
        return this;
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