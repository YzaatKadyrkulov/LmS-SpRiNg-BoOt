package peaksoft.lmsspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_gen")
    @SequenceGenerator(name ="task_gen",sequenceName = "task_seq",allocationSize = 1,initialValue = 1)
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadline;
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,})
    private Lesson lesson;
}
