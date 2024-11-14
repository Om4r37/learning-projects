package com.example.jspdemo;


import jakarta.validation.constraints.*;
import java.util.Objects;

public class User {
    private int id;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    private String email;

//    @Pattern.List({
//        @Pattern(message = "Password must contain at least 3 digits.", regexp = "(?=(.*\\d){3,})"),
//        @Pattern(message = "Password must contain at least 3 letters.", regexp = "(?=(.*[a-zA-Z]){3,})"),
//        @Pattern(message = "Password must contain at least 1 uppercase letter.", regexp = "(?=.*[A-Z])"),
//        @Pattern(message = "Password must contain at least 1 lowercase letter.", regexp = "(?=.*[a-z])")
//    })
    @NotBlank(message = "Password is required.")
//    @Size(min = 8, message = "Password must be at least 8 characters.")
    private String password;

    @NotBlank(message = "Please confirm your password.")
    private String passwordConfirm;

//    @AssertTrue(message = "Passwords Don't Match.")
//    private boolean passwordMatch;

    private String hash;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Min(value = 1900, message = "Birth year must be after 1900.")
    @Max(value = 2024, message = "Birth year cannot be after 2024.")
    private int birthYear;

    public User(){}

    public User(int id, String email, String password, String passwordConfirm, String hash, String firstName, String lastName, int birthYear) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.hash = hash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public boolean isValid() {
        if (validPassword() && validPasswordConfirm() && validBirthYear()) return true;
        return false;
    }

//    public ArrayList<String> getErrors(){
//        ArrayList<String> errors = new ArrayList<>();
//        if (!validPassword()) errors.add("password");
//        if (!validPasswordConfirm()) errors.add("passwordConfirm");
//        if (!validBirthYear()) errors.add("birthYear");
//        return errors;
//    }

    @AssertTrue(message = "Passwords Don't Match.")
    public boolean isPasswordMatch() {
        return password != null && password.equals(passwordConfirm);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validPassword() {
        return passwordErrors().isEmpty();
    }
    public String passwordErrors() {
        String errors = "";
        if (password.length() < 8) errors += "<br>Password must be at least 8 characters.";
        if (password.toLowerCase().equals(password)) errors += "<br>Password must contain at least one uppercase letter.\n";
        if (password.toUpperCase().equals(password)) errors += "<br>Password must contain at least one lowercase letter.\n";
        return errors;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean validPasswordConfirm() {
        return password.equals(passwordConfirm);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public boolean validBirthYear() {
        return birthYear > 1900 && birthYear < 2024;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && birthYear == user.birthYear && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(passwordConfirm, user.passwordConfirm) && Objects.equals(hash, user.hash) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, passwordConfirm, hash, firstName, lastName, birthYear);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", hash='" + hash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}