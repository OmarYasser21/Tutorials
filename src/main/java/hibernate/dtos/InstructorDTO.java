package hibernate.dtos;

import hibernate.entities.HibernateCourse;
import hibernate.entities.InstructorDetails;

import java.util.List;
import java.util.stream.Collectors;

public class InstructorDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String title;
    private List<CourseDTO> courses;
    private InstructorDetailsDTO instructorDetails;


    public InstructorDetailsDTO getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetailsDTO instructorDetails) {
        this.instructorDetails = instructorDetails;
    }



    public InstructorDTO(String firstName, String lastName, String phoneNumber, String email, String title, InstructorDetailsDTO instructorDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.instructorDetails = instructorDetails;
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
    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public static void printInstructor(InstructorDTO dto) {
        System.out.println("Instructor Details:");
        System.out.println("First Name: " + dto.getFirstName());
        System.out.println("Last Name: " + dto.getLastName());
        System.out.println("Phone Number: " + dto.getPhoneNumber());
        System.out.println("Email: " + dto.getEmail());
        System.out.println("Title: " + dto.getTitle());

        // Print InstructorDetails if available
        if (dto.getInstructorDetails() != null) {
            System.out.println("Hobbies: " + dto.getInstructorDetails().getHobbies());
            System.out.println("YouTube Channel: " + dto.getInstructorDetails().getYoutubeChannel());
        }

        // Print associated courses
        List<CourseDTO> courses = dto.getCourses();
        if (courses != null && !courses.isEmpty()) {
            System.out.print("Courses Taught: ");
            String courseNames = courses.stream()
                    .map(CourseDTO::getName)
                    .collect(Collectors.joining(", "));
            System.out.println(courseNames);
        } else {
            System.out.println("No courses associated with this instructor.");
        }

        System.out.println();
    }


}
