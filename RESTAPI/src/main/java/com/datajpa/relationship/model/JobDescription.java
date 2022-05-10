package com.datajpa.relationship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job_description")
public class JobDescription {
    @Id
    @GeneratedValue
    private long id;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "desc_id")
    private Job job_descriptions;

}
