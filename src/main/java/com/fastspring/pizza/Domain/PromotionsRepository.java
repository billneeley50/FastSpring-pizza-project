package com.fastspring.pizza.Domain;

import com.fastspring.pizza.Domain.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionsRepository extends CrudRepository<Promotion, Long> {


    public Promotion findByPromotionCode(String promotionCode);


}
