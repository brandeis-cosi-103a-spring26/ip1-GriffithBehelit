package edu.brandeis;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import edu.brandeis.cosi103a.ip1.Card;
import edu.brandeis.cosi103a.ip1.DominionGame;
import edu.brandeis.cosi103a.ip1.Player;


public class DominionGameTest {
    
        @Test
        public void testStartingHands() {
            // Create a new game manually inside the test
            DominionGame game = new DominionGame();
            game.setup();
    
            Player player1 = game.getPlayer1();
            Player player2 = game.getPlayer2();
    
            // Check that each player starts with 5 cards in hand
            assertEquals(5, player1.getHand().size());
            assertEquals(5, player2.getHand().size());
        }
    
        @Test
        public void testStartingDeckCounts() {
            DominionGame game = new DominionGame();
            game.setup();
    
            Player p1 = game.getPlayer1();
            Player p2 = game.getPlayer2();
    
            // Total cards in draw pile + hand + discard pile = 10 for starter deck
            int totalP1 = p1.getDrawPileSize() + p1.getDiscardPileSize() + p1.getHand().size();
            int totalP2 = p2.getDrawPileSize() + p2.getDiscardPileSize() + p2.getHand().size();
    
            assertEquals(10, totalP1);
            assertEquals(10, totalP2);
        }
    
        @Test
        public void testPurchasePriority() {
            DominionGame game = new DominionGame();
            game.setup();
    
            List<Card> priority = game.getPurchasePriority();
            assertNotNull(priority);
            assertEquals(6, priority.size());
            assertEquals("Framework", priority.get(0).getName());
            assertEquals("Bitcoin", priority.get(5).getName());
        }
    }