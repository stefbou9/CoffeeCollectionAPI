package org.example.coffeecollectionapi.repository;

import org.example.coffeecollectionapi.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tell Spring: This is a bean.
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {}