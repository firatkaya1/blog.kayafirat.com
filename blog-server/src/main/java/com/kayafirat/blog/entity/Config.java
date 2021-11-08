package com.kayafirat.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "config")
public class Config {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long configId;

    private String configDescription;
    private String configKod;
    private String configValue;
}
