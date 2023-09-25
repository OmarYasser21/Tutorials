package hibernate.dtos;

import hibernate.entities.HibernateCourse;
import hibernate.entities.InstructorDetails;

import java.util.List;

public class InstructorDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String title;
    private List<HibernateCourse> courses;
    private InstructorDetails instructorDetails;


    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }



    public InstructorDTO(String firstName, String lastName, String phoneNumber, String email, String title, InstructorDetails instructorDetails, List<HibernateCourse> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.instructorDetails = instructorDetails;
        this.courses = courses;
    }

    public InstructorDTO(){}
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<HibernateCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<HibernateCourse> courses) {
        this.courses = courses;
    }
    @Override
    public String toString() {
        return "InstructorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
