
package com.qader.ahmed.companyandmembers.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company withId(String id) {
        this.id = id;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Company withCompany(String company) {
        this.company = company;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company withWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Company withLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Company withAbout(String about) {
        this.about = about;
        return this;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Company withMembers(List<Member> members) {
        this.members = members;
        return this;
    }

}
