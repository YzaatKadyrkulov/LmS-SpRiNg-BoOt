package peaksoft.lmsspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.lmsspringboot.entity.Company;
import peaksoft.lmsspringboot.entity.Course;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "in_gen")
    @SequenceGenerator(name ="in_gen",sequenceName = "in_seq",allocationSize = 1,initialValue = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    @ManyToMany(cascade = {DETACH,REFRESH,MERGE,})
    private List<Company> companies = new ArrayList<>();
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,})
    private Course course;

}
