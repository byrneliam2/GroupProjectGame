package frames.tests;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import frames.cards.Card;
import frames.cards.MenuCard;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTests {

    @Test
    public void test01_BasicCard() {
        Card c = new MenuCard("menu");
        assertTrue(c.getName().equals("menu"));
    }
}
