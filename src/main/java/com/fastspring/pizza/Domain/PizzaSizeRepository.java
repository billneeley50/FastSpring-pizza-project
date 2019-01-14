package com.fastspring.pizza.Domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaSizeRepository extends CrudRepository<PizzaSize, Long> {

}
