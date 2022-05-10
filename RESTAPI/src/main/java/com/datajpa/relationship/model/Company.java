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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue
    private long id;
    private String companyName;
    private String email;

}