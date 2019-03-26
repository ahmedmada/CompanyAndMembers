
package com.qader.ahmed.companyandmembers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Member implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("age")
    @Expose
    private Long age;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member withId(String id) {
        this.id = id;
        return this;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Member withAge(Long age) {
        this.age = age;
        return this;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Member withName(Name name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Member withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Member withPhone(String phone) {
        this.phone = phone;
        return this;
    }

}
