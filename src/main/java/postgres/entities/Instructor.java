package postgres.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Instructor implements EntityMapper<Instructor> {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String title;

    public Instructor(int id, String firstName, String lastName, String email, String phoneNumber, String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public Instructor() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public Instructor map(ResultSet resultSet) throws SQLException {
        // Extract data from the ResultSet and create an Instructor instance
        return new Instructor(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getString("title")
        );
    }


}

