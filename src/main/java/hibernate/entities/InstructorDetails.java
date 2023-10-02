package hibernate.entities;

import jakarta.persistence.*;
import postgres.entities.Instructor;

import java.util.UUID;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @OneToOne(mappedBy = "instructorDetails")
    private HibernateInstructor instructor;

    public InstructorDetails(String hobbies, String youtubeChannel) {
        this.hobbies = hobbies;
        this.youtubeChannel = youtubeChannel;
    }
    public InstructorDetails() {}

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
}
