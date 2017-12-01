package prvaaplikacija.vtoraaplikacija;

import java.io.Serializable;

/**
 * Created by Mende on 26.11.2017.
 */

public class Korisnik implements Serializable{

    public String name;
    public String lastname;
    public String username;
    public Boolean gender;

    public Korisnik(String name, String lastname,String username, Boolean gender) {
        this.name=name;
        this.lastname=lastname;
        this.username=username;
        this.gender=gender;
    }
    public Korisnik(){
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGender(Boolean male) {
        this.gender = male;
    }

    @Override
    public String toString() {
        return name;
    }
}
