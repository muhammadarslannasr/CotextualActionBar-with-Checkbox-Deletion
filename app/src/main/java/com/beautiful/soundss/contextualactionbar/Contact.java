package com.beautiful.soundss.contextualactionbar;

/**
 * Created by ArslanNasr on 9/18/2018.
 */

public class Contact {

    int image;
    String name;
    String email;

    public Contact(int image, String name, String email) {
        this.image = image;
        this.name = name;
        this.email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
