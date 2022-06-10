package dev.overlax.cyberlibrary.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Full Name shouldn't be empty!")
    @Size(min = 2, max = 100, message = "Full Name should be between 2 and 100 characters.")
    private String fullName;

    @Min(value = 1900, message = "Min date of birth should be greater than 1900.")
    @Max(value = 2022, message = "Max date of birth should be less than 2022.")
    private int birthDay;

    public Person() {
    }

    public Person(String fullName, int birthDay) {
        this.fullName = fullName;
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }
}
