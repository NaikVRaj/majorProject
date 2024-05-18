package com.spe.workwize.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@JsonIgnoreProperties({"project"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false, unique = true)
    private String taskName;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private TaskStatusLu statusLu;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}

