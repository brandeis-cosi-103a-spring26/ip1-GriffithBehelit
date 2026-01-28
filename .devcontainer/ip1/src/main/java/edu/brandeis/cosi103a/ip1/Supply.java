package edu.brandeis.cosi103a.ip1;

import java.util.HashMap;
import java.util.Map;

public class Supply {
    private Map<Card, Integer> piles = new HashMap<>();

    public void addPile(Card card, int count) {
        piles.put(card, count);
    }

    public boolean isAvailable(Card card) {
        return piles.getOrDefault(card, 0) > 0;
    }

    public Card buy(Card card) {
        if (!isAvailable(card)) return null;
        piles.put(card, piles.get(card) - 1);
        return card;
    }

    public int getRemaining(Card card) {
        return piles.getOrDefault(card, 0);
    }
}
