package edu.brandeis.cosi103a.ip1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    private List<Card> drawPile = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();
    private List<Card> hand = new ArrayList<>();

    private Random random = new Random();

    public void shuffleDrawPile() {
        Collections.shuffle(drawPile, random);
    }

    public void drawCard() {
        if (drawPile.isEmpty()) {
            if (discardPile.isEmpty()) return;
            drawPile.addAll(discardPile);
            discardPile.clear();
            shuffleDrawPile();
        }
        hand.add(drawPile.remove(0));
    }

    public void drawHand(int n) {
        for (int i = 0; i < n; i++) {
            drawCard();
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public void discardHand() {
        discardPile.addAll(hand);
        hand.clear();
    }

    public void gainCard(Card card) {
        discardPile.add(card);
    }

    public List<Card> getAllCards() {
        List<Card> all = new ArrayList<>();
        all.addAll(drawPile);
        all.addAll(hand);
        all.addAll(discardPile);
        return all;
    }
    public void addToDrawPile(Card card) {
        drawPile.add(card);
    }
    
    public int getDrawPileSize() {
        return drawPile.size();
    }
    
    public int getDiscardPileSize() {
        return discardPile.size();
    }

}
