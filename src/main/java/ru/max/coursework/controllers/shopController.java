package ru.max.coursework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.max.coursework.entities.Cart;
import ru.max.coursework.entities.User;
import ru.max.coursework.services.CartService;
import ru.max.coursework.services.UserService;

@Controller
public class shopController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    private void workWithCart(User user, int bouquetNumber, boolean plus){
        if (user.getBCount(bouquetNumber) == 0){
            if (!plus)
                return;
            Cart cart = new Cart(user, bouquetNumber);
            cart.setbouquetCount(1);
            user.getList().add(cart);
            cartService.addCart(cart);
        }   else {
                Cart cart = user.getB(bouquetNumber);
                if (user.getBCount(bouquetNumber) == 1 & !plus){
                    user.getList().remove(cart);
                    userService.rootResaveUser(user);
                    cartService.deleteCart(cart);
                }
                else
                    cartService.changeCountOfBouquet(cart, plus);
            }
    }

    @GetMapping("/add_b{number:\\d+}")
    public String addB(@PathVariable int number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        workWithCart((User) auth.getPrincipal(), number, true);
        return "redirect:/shop#b" + String.valueOf(number);
    }

    @GetMapping("/rem_b{number:\\d+}")
    public String remB(@PathVariable int number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        workWithCart((User) auth.getPrincipal(), number, false);
        return "redirect:/shop#b" + String.valueOf(number);
    }


}
