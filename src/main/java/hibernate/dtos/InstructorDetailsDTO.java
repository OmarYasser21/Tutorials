package hibernate.dtos;

import hibernate.entities.HibernateInstructor;
import postgres.entities.Instructor;

import java.util.UUID;

public class InstructorDetailsDTO {
    private UUID id;
    private String hobbies;
    private String youtubeChannel;
    private HibernateInstructor instructor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public HibernateInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(HibernateInstructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetailsDTO(String hobbies, String youtubeChannel) {
        this.hobbies = hobbies;
        this.youtubeChannel = youtubeChannel;
    }

    public InstructorDetailsDTO() {
    }

}
