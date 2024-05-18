package com.spe.workwize.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long projectId;

    @Column(unique = true, nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROJECT_USER",
            joinColumns = {
                    @JoinColumn(name = "PROJECT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID") })
    private Set<User> users;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    private Set<Task> tasks;

    @OneToOne(mappedBy = "project",cascade = CascadeType.REMOVE)
    private EffortTable effortTable;
}

