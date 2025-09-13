package com.codingbattle.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * User entity
 */
public class User {
    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * User username
     */
    @Column(unique = true, nullable = false)
    private String username;
    /**
     * User email
     */
    @Column(unique = true, nullable = false)
    private String email;
    /**
     * User password
     */
    @Column(nullable = false)
    private String password;
    /**
     * User role
    */
    @Column(nullable = false)
    private String role;
    /**
     * User rating (ELO rating)
    */
    @Builder.Default
    @Column(nullable = false)
    private Integer rating = 1200;


    /**
     * User empty constructor
     */
    public User() {
    }

    /**
     * User constructor with username, email, password, role
     */
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
     
    /**
     * User constructor with id
     */
    public User(String id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Get username
     */
    public getUsername() {
        return username;
    }

    /**
     * Set username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get role
     */
    public String getRole() {
        return role;
    }
    /**
     * Set role
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Get rating
     */
    public Integer getRating() {
        return rating;
    }
    /**
     * Set rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    /**
     * Get id
     */
    public Long getId() {
        return id;
    }
    /**
     * Set id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}


