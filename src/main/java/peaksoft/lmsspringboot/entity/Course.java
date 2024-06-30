package peaksoft.lmsspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_gen")
    @SequenceGenerator(name ="course_gen",sequenceName = "course_seq",allocationSize = 1,initialValue = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    @ManyToOne
    private Company company ;

    @OneToMany(mappedBy = "course",cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    private List<Instructor> instructor = new ArrayList<>();

    @ManyToMany(cascade = {DETACH,REFRESH})
    private List<Group> groups =  new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = {REMOVE,REFRESH})
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String courseName, LocalDate dateOfStart, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}
