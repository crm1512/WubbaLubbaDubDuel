package repositories;


import models.Card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
	
    List<Card> findAll();
    
    Card findByName(String name);
    
    List<Card> findByCostLessThanEqual(int cost);
    
    
}
