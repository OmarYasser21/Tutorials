package hibernate.entities;

import hibernate.enums.Level;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
public class HibernateCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "is_started")
    private boolean isStarted;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private HibernateInstructor instructor;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<HibernateStudent> students;
    public HibernateCourse(){}
    public HibernateCourse(String name, Timestamp startDate, Timestamp endDate, Level level, boolean isStarted, HibernateInstructor instructor, Set<HibernateStudent> students) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.isStarted = isStarted;
        this.instructor = instructor;
        this.students = students;
    }

    public Set<HibernateStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<HibernateStudent> students) {
        this.students = students;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HibernateInstructor getInstructor() {
        return instructor;
    }

    public void setInstructor(HibernateInstructor instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
