package com.hackdroid.droidcontacts.Model;

import android.graphics.Bitmap;
import android.net.Uri;

public class ContactModel {
    public String id;
    public String name;
    public String mobileNumber;
    public Bitmap photo;
    public Uri photoURI;

    public ContactModel() {
    }

    public ContactModel(String id, String name, String mobileNumber, Bitmap photo, Uri photoURI) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.photo = photo;
        this.photoURI = photoURI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Uri getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(Uri photoURI) {
        this.photoURI = photoURI;
    }
}
