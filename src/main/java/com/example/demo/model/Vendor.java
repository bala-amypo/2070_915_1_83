package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 39]

    @Column(unique = true, nullable = false)
    private String name; // [cite: 42, 51]

    private String contactEmail; // [cite: 43]
    private String contactPhone; // [cite: 44]
    private Boolean active = true; // [cite: 45, 52]

    @CreationTimestamp
    private Timestamp createdAt; // [cite: 47]

    @UpdateTimestamp
    private Timestamp updatedAt; // [cite: 48]

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}