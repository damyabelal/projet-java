package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.action.actionDemeter.PlayThief;
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

    @Test
    void shouldStealRessourcesSuccessfully() throws NoMoreRessourcesException {
        player1.addRessource(Ressource.WOOD, 10);
        player2.addRessource(Ressource.WOOD, 5);

        player2.addThief();
        
        PlayerDemeter[] listPlayers = {player1, player2};
        actionVoler = new PlayThief(Ressource.WOOD, listPlayers);
        
        actionVoler.act(player2);

        assertEquals(0, player1.getRessourceAmount(Ressource.WOOD));
        assertEquals(15, player2.getRessourceAmount(Ressource.WOOD));
    }

    @Test
    void shouldThrowExceptionWhenPlayerHasNoThief() {
        player1.addRessource(Ressource.WOOD, 10);
        PlayerDemeter[] listPlayers = {player1};
        actionVoler = new PlayThief(Ressource.WOOD, listPlayers);
        assertThrows(NoMoreRessourcesException.class, () -> actionVoler.act(player1));
    }

  }