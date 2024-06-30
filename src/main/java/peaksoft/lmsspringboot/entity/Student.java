package peaksoft.lmsspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.lmsspringboot.FormatStudy;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "st_gen")
    @SequenceGenerator(name ="st_gen",sequenceName = "st_seq",allocationSize = 1,initialValue = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private FormatStudy formatStudy;
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,})
    private Group group;
}
