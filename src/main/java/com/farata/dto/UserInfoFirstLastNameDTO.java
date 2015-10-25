package com.farata.dto;

/**
 * Created by andrei on 20.10.2015.
 */
public class UserInfoFirstLastNameDTO {

    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserInfoFirstLastNameDTO(long id, String firstName, String lastName) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
