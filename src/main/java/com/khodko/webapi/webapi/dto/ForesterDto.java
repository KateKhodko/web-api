package com.khodko.webapi.webapi.dto;

import lombok.Builder;

@Builder
public class ForesterDto implements BaseDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String imageId;

    public ForesterDto(Long id, String firstname, String lastname, String imageId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageId = imageId;
    }

    public ForesterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
