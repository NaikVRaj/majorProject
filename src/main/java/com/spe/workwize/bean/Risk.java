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
public class Risk {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long riskId;

    @Column
    private String description;

    @Column
    private String  status;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
