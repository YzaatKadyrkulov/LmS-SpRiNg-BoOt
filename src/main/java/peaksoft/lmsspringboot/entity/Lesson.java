package peaksoft.lmsspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lesson_gen")
    @SequenceGenerator(name ="lesson_gen",sequenceName = "lesson_seq",allocationSize = 1,initialValue =1)
    private Long id;
    private String LessonName;
    @ManyToOne(cascade = {DETACH,},fetch =FetchType.EAGER)
    private Course course;
    @OneToMany(mappedBy = "lesson",cascade = {REMOVE,REFRESH})
    private List<Task> tasks  = new ArrayList<>();
}
