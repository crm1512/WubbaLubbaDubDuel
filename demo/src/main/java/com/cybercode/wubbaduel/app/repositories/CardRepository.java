package com.cybercode.wubbaduel.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cybercode.wubbaduel.app.models.*;
import java.util.List;
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAll();

    Card findByName(String name);

    List<Card> findByCostLessThanEqual(int cost);
}
