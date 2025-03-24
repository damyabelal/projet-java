package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayThiefTest {

    private PlayerDemeter player1;
    private PlayerDemeter player2;
    private PlayThief actionVoler;

    @BeforeEach
    void setUp() {
        player1 = new PlayerDemeter("lucie");
        player2 = new PlayerDemeter("luca");
    }

    @Test
    void shouldThrowExceptionWhenNoRessources() throws NoMoreRessourcesException {
        PlayerDemeter[] listPlayers = {player2};
        actionVoler = new PlayThief(Ressource.WOOD, listPlayers);
        
        assertEquals(0, player1.getRessourceAmount(Ressource.WOOD));
        assertEquals(0, player2.getRessourceAmount(Ressource.WOOD));
        
        assertThrows(NoMoreRessourcesException.class, () -> actionVoler.act(player2));
    }

  }