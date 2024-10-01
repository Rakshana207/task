package org.project.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})   //for duplicate emails
})
public class Users {
    @Id  //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //should be generated automatically
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
}

