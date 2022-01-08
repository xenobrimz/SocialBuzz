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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="cat got your tongue?")
    @Size(max=3, message="This buzz is too large for this town")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buzz_id")
    private Buzz buzz;

    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;


    public Comment() {
    }

    public Comment(Long id, String comment, User user, Buzz buzz, Date createdAt, Date updatedAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.buzz = buzz;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Buzz getBuzz() {
        return this.buzz;
    }

    public void setBuzz(Buzz buzz) {
        this.buzz = buzz;
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

    public Comment id(Long id) {
        setId(id);
        return this;
    }

    public Comment comment(String comment) {
        setComment(comment);
        return this;
    }

    public Comment user(User user) {
        setUser(user);
        return this;
    }

    public Comment buzz(Buzz buzz) {
        setBuzz(buzz);
        return this;
    }

    public Comment createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Comment updatedAt(Date updatedAt) {
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
