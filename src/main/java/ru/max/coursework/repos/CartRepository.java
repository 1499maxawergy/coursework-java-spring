package ru.max.coursework.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.max.coursework.entities.Cart;
import ru.max.coursework.entities.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserID(User user);
    Cart findCartByUserIDAndBouquetNumber(User user, int bouquetNumber);
}
