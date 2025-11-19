package org.example.coffeecollectionapi.service;

import org.example.coffeecollectionapi.model.Coffee;
import org.example.coffeecollectionapi.model.CoffeeDTO;
import org.example.coffeecollectionapi.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {

    private final CoffeeRepository repository;

    @Autowired
    public CoffeeService(CoffeeRepository repository) {
        this.repository = repository;
    }

    public CoffeeDTO createCoffee(CoffeeDTO coffeeDTO) {
        Coffee coffee = mapToEntity(coffeeDTO);
        Coffee savedCoffee = repository.save(coffee);
        return mapToDTO(savedCoffee);
    }

    public List<CoffeeDTO> getAllCoffees() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CoffeeDTO> getCoffeeById(Long id) {
        return repository.findById(id).map(this::mapToDTO);
    }

    public Optional<CoffeeDTO> updateCoffee(Long id, CoffeeDTO coffeeDTO) {
        return repository.findById(id).map(existingCoffee -> {
            existingCoffee.setName(coffeeDTO.getName());
            existingCoffee.setOrigin(coffeeDTO.getOrigin());
            existingCoffee.setRoast(coffeeDTO.getRoast());
            existingCoffee.setPrice(coffeeDTO.getPrice());
            existingCoffee.setTastingNotes(coffeeDTO.getTastingNotes());
            Coffee updatedCoffee = repository.save(existingCoffee);
            return mapToDTO(updatedCoffee);
        });
    }

    public boolean deleteCoffee(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private CoffeeDTO mapToDTO(Coffee coffee) {
        return new CoffeeDTO(
                coffee.getId(),
                coffee.getName(),
                coffee.getOrigin(),
                coffee.getRoast(),
                coffee.getPrice(),
                coffee.getTastingNotes());
    }

    private Coffee mapToEntity(CoffeeDTO coffeeDTO) {
        Coffee coffee = new Coffee();
        coffee.setName(coffeeDTO.getName());
        coffee.setOrigin(coffeeDTO.getOrigin());
        coffee.setRoast(coffeeDTO.getRoast());
        coffee.setPrice(coffeeDTO.getPrice());
        coffee.setTastingNotes(coffeeDTO.getTastingNotes());
        return coffee;
    }
}
