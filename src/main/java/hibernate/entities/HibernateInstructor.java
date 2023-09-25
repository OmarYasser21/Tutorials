package hibernate.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "instructor")
public class HibernateInstructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "instructor")
    private List<HibernateCourse> courses;

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }

    @OneToOne(mappedBy = "instructor")
    private InstructorDetails instructorDetails;

    public List<HibernateCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<HibernateCourse> courses) {
        this.courses = courses;
    }

    public HibernateInstructor(String firstName, String lastName, String phoneNumber, String email, String title, InstructorDetails instructorDetails, List<HibernateCourse> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.instructorDetails = instructorDetails;
        this.courses = courses;
    }
    public HibernateInstructor(){}


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
}
