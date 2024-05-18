package com.spe.workwize.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"project"})
public class EffortTable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long effortTableId;

    @Column(nullable = false)
    private Integer requirementAnalysisHours;

    @Column(nullable = false)
    private Integer designingHours;

    @Column(nullable = false)
    private Integer codingHours;

    @Column(nullable = false)
    private Integer testingHours;

    @Column(nullable = false)
    private Integer projectManagementHours;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
