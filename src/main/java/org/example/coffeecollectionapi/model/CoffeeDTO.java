package org.example.coffeecollectionapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CoffeeDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotNull(message = "Roast is required")
    private Roast roast;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String tastingNotes;

    public CoffeeDTO() {
    }

    public CoffeeDTO(Long id, String name, String origin, Roast roast, BigDecimal price, String tastingNotes) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.roast = roast;
        this.price = price;
        this.tastingNotes = tastingNotes;
    }

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
