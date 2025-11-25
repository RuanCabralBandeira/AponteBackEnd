package com.senac.aponte.entity;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;

    @Column(name = "tag_name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileTag> profileTags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProfileTag> getProfileTags() {
        return profileTags;
    }

    public void setProfileTags(List<ProfileTag> profileTags) {
        this.profileTags = profileTags;
    }
}
