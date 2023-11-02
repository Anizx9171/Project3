package project.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart implements Serializable {
    Long UserId;
    Map<Long, Integer> cart = new ConcurrentHashMap<>();

    public Cart(Long userId) {
        UserId = userId;
    }

    public void setCart(Map<Long, Integer> cart) {
        this.cart = cart;
    }

    public Map<Long, Integer> getCart() {
        return cart;
    }

    public Long getUserId() {
        return UserId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "UserId=" + UserId +
                ", cart=" + cart +
                '}';
    }
}