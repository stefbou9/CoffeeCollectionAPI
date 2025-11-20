package org.example.coffeecollectionapi.controller;

import jakarta.validation.Valid;
import org.example.coffeecollectionapi.model.CoffeeDTO;
import org.example.coffeecollectionapi.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    private final CoffeeService service;

    @Autowired
    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    // 1. CREATE
    @PostMapping
    public ResponseEntity<CoffeeDTO> createCoffee(@Valid @RequestBody CoffeeDTO coffeeDTO) {
        CoffeeDTO createdCoffee = service.createCoffee(coffeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffee);
    }

    // 2. READ (All)
    @GetMapping
    public ResponseEntity<List<CoffeeDTO>> getAllCoffees() {
        List<CoffeeDTO> coffees = service.getAllCoffees();
        return ResponseEntity.ok(coffees);
    }

    // 3. READ (One by ID)
    @GetMapping("/{id}")
    public ResponseEntity<CoffeeDTO> getCoffeeById(@PathVariable Long id) {
        Optional<CoffeeDTO> coffee = service.getCoffeeById(id);
        return coffee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CoffeeDTO> updateCoffee(@PathVariable Long id, @Valid @RequestBody CoffeeDTO coffeeDTO) {
        Optional<CoffeeDTO> updatedCoffee = service.updateCoffee(id, coffeeDTO);
        return updatedCoffee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        if (service.deleteCoffee(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
