package com.softplan.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seem {

    @Id
    @GeneratedValue
    private Long id;
    private Instant date;
    private String title;
    private String description;

    @ManyToOne(cascade= CascadeType.PERSIST)
    private User user;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Process process;
}
