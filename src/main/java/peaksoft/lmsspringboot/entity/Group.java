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
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "group_gen")
    @SequenceGenerator(name ="group_gen",sequenceName = "group_seq",allocationSize = 1,initialValue = 1)
    private Long id;
    private String groupName;
    @Column(length = 20000)
    private String imageLink;
    private String description;
    @ManyToMany(mappedBy = "groups",cascade = {DETACH,REFRESH})
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = {REMOVE,REFRESH})
    private List<Student> students = new ArrayList<>();
}
