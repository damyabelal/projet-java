package game.action;
import game.PlayerDemeter;



import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRessourcesTest {

    private PlayerDemeter player;

    @BeforeEach
    void setUp() {
        player = new PlayerDemeter("Demeter");
        
    }

    /**
     * Tests a valid exchange: 3 WOOD et 1 ORE
     * @throws Exception 
     */
    @Test
    void testValidExchange() throws Exception {
        player.addRessource(Ressource.WOOD, 3);
        player.addRessource(Ressource.ORE, 1);

        int beforeWood = player.getRessourceAmount(Ressource.WOOD);
        int beforeOre = player.getRessourceAmount(Ressource.ORE);

        ExchangeRessources<PlayerDemeter> testAction = new ExchangeRessources<>(player) {
            @Override
            public Ressource askExchangeRessources() {
                return Ressource.WOOD;
            }

            @Override
            public Ressource askReceiveRessources() {
                return Ressource.ORE;
            }
        };

        testAction.act(player);

        assertEquals(beforeWood - 3, player.getRessourceAmount(Ressource.WOOD));
        assertEquals(beforeOre + 1, player.getRessourceAmount(Ressource.ORE));
    }

    /**
     * Tests exception when trying to exchange the same resource.
     */
    @Test
    void testSameResourceExchangeThrows() {
        player.addRessource(Ressource.WOOD, 3);

        ExchangeRessources<PlayerDemeter> testAction = new ExchangeRessources<>(player) {
            @Override
            public Ressource askExchangeRessources() {
                return Ressource.WOOD;
            }

            @Override
            public Ressource askReceiveRessources() {
                return Ressource.WOOD;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> testAction.act(player));
    }

    /**
     * Tests exception when the player has less than 3 of the resource to exchange.
     */
    @Test
    void testNotEnoughResourcesThrows() {
        player.addRessource(Ressource.WOOD, 2);

        ExchangeRessources<PlayerDemeter> testAction = new ExchangeRessources<>(player) {
            @Override
            public Ressource askExchangeRessources() {
                return Ressource.WOOD;
            }

            @Override
            public Ressource askReceiveRessources() {
                return Ressource.ORE;
            }
        };

        assertThrows(NoMoreRessourcesException.class, () -> testAction.act(player));
    }
}
