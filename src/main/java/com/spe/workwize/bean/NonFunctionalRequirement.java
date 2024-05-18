package com.spe.workwize.bean;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NonFunctionalRequirement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long nfrId;

    @Column
    private String requirement;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;
}

