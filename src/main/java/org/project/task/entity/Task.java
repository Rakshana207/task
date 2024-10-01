package org.project.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="taskname",nullable = false)
    private  String taskname;
    @ManyToOne(fetch = FetchType.LAZY)  //many tasks are given to one person....
    // Lazy->we don't have to take details of user(available in users entity)(opp... eager)
    @JoinColumn(name="users_id")   //foreign key
    private Users user;  //take from users entity(object)
}

