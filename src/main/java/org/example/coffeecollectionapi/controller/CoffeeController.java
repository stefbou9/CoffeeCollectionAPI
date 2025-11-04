package org.example.coffeecollectionapi.controller;

import org.example.coffeecollectionapi.model.Coffee;
import org.example.coffeecollectionapi.repository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    private final CoffeeRepository repository;

    // Constructor Injection
    public CoffeeController(CoffeeRepository repository) {
        this.repository = repository;
    }

    // --- C-R-U-D ENDPOINTS ---

    // 1. CREATE
    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee savedCoffee = repository.save(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffee);
    }

    // 2. READ (All)
    @GetMapping
    public List<Coffee> getAllCoffees() {
        return repository.findAll();
    }

    // 3. READ (One by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        Optional<Coffee> coffee = repository.findById(id);

        if (coffee.isPresent()) {
            return ResponseEntity.ok(coffee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee newCoffeeData) {
        Optional<Coffee> optionalCoffee = repository.findById(id);

        // Check if exists
        if (optionalCoffee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Get the existing coffee and update its fields
        Coffee existingCoffee = optionalCoffee.get();
        existingCoffee.setName(newCoffeeData.getName());
        existingCoffee.setOrigin(newCoffeeData.getOrigin());
        existingCoffee.setRoast(newCoffeeData.getRoast());
        existingCoffee.setPrice(newCoffeeData.getPrice());
        existingCoffee.setTastingNotes(newCoffeeData.getTastingNotes());

        // Save the updated coffee
        Coffee updatedCoffee = repository.save(existingCoffee);
        return ResponseEntity.ok(updatedCoffee);
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        // Check if exists
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
