package com.datajpa.relationship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "recruiters")
public class Recruiter {
    @Id
    @GeneratedValue
    private long emailId;
    private String firstName;
    private String lastName;
    private int salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_id")
    private Company company;


}

