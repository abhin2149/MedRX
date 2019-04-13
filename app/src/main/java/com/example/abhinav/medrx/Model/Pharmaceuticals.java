

package com.example.abhinav.medrx.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pharmaceuticals {

    @SerializedName("contact")
    @Expose
    private Integer contact;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("medicine")
    @Expose
    private String medicine;
    @SerializedName("expirydate")
    @Expose
    private String expirydate;


    /**
     * No args constructor for use in serialization
     *
     */
    public Pharmaceuticals() {
    }

    /**
     *
     * @param username
     * @param _long
     * @param name
     * @param userid
     * @param password
     * @param contact
     * @param lat
     * @param address
     * @param medicine
     * @param expirydate
     */
    public Pharmaceuticals(Integer contact, Double lat, Double _long, String name, String password, Integer userid, String username,String address,String medicine,String expirydate) {
        super();
        this.contact = contact;
        this.lat = lat;
        this._long = _long;
        this.name = name;
        this.password = password;
        this.userid = userid;
        this.username = username;
        this.address=address;
        this.medicine=medicine;
        this.expirydate=expirydate;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }
}
