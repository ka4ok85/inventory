package com.example.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "users")
public class User implements Persistable<Long> {

    private static final long serialVersionUID = -2245681232129182950L;

    @JsonView(com.example.entity.User.class)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(com.example.entity.User.class)
    @Column(name = "login", nullable = false)
    private String login;

    @JsonView(com.example.entity.User.class)
    @Column(name = "password", nullable = false)
    private String password;

    @JsonView(com.example.entity.User.class)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @JsonView(com.example.entity.User.class)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="user_id")
    private Set<Userstore> userstores = new HashSet<Userstore>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Userstore> getUserstores() {
        return userstores;
    }

    public void setUserstores(Set<Userstore> userstores) {
        this.userstores = userstores;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
               + ", lastName=" + lastName +  "]";
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        User that = (User) o;
        return Objects.equals(login, that.login) &&
               Objects.equals(password, that.password) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName);
    }
}
