package com.softplan.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Process {

    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String city;
    private String stateOrProvince;
    private Boolean pending;
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Seem> seems;

}