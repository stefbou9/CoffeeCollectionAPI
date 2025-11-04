package org.example.coffeecollectionapi.model;

import jakarta.persistence.*; // Necessary to use the @Annotations
import java.math.BigDecimal;  // Needed for the price

@Entity
@Table(name = "coffees")
public class Coffee {

    @Id // Mark this field as the table's Primary Key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate this ID.
    private Long id;

    @Column(nullable = false) // This column cannot be null.
    private String name;

    private String origin;

    @Enumerated(EnumType.STRING) // Store ENUM as String
    private Roast roast;

    private BigDecimal price;

    private String tastingNotes;

    public Coffee() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Roast getRoast() {
        return roast;
    }

    public void setRoast(Roast roast) {
        this.roast = roast;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTastingNotes() {
        return tastingNotes;
    }

    public void setTastingNotes(String tastingNotes) {
        this.tastingNotes = tastingNotes;
    }
}