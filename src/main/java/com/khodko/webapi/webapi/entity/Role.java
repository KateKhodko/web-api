package com.khodko.webapi.webapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements BaseEntity {
    @Id
    private Long id;
    private String name;
    @Transient
    @OneToMany(mappedBy = "role")
    private Set<Profile> profiles;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getUsers() {
        return profiles;
    }

    public void setUsers(Set<Profile> profiles) {
        this.profiles = profiles;
    }
}