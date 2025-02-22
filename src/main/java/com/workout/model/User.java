package com.workout.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate registeredDate;

    @Column(unique = true, nullable = false)
    private String personalId; // UUID stored as String

    @Column(nullable = false)
    private String passwordHash; // Hashed password

    // Constructors
    public User(String firstName, String lastName, String personalId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.registeredDate = LocalDate.now();
        this.passwordHash = hashPassword(password);
    }

    public User() {}

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getRegisteredDate() { return registeredDate; }
    public String getPersonalId() { return personalId; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Password handling
    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public boolean checkPassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.passwordHash);
    }

    public void setPassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }
}
