package com.fastspring.pizza.Domain;

import com.fastspring.pizza.Domain.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
