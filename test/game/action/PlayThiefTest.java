package game.action;

import game.PlayerDemeter;
import game.action.actionDemeter.PlayThief;
import game.listchooser.FakeListChooser;
import game.listchooser.ListChooser;
import game.listchooser.RandomListChooser;
import game.tuile.Ressource;
import game.util.NoMoreRessourcesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
        List<PlayerDemeter> listPlayers = new ArrayList<>();
        
        listPlayers.add(player1);
        listPlayers.add(player2);
        actionVoler = new PlayThief(new RandomListChooser<>(), listPlayers);
        
        assertEquals(0, player1.getRessourceAmount(Ressource.WOOD));
        assertEquals(0, player2.getRessourceAmount(Ressource.WOOD));
        
        assertThrows(NoMoreRessourcesException.class, () -> actionVoler.act(player2));
    }

    @Test
    void shouldStealRessourcesSuccessfully() throws NoMoreRessourcesException {
        // vider les ressources des joueurs
        
        
        player1.addRessource(Ressource.WOOD, 10);
        player2.addThief();
        
        List<PlayerDemeter> listPlayers = new ArrayList<>();
        listPlayers.add(player1);
        listPlayers.add(player2);
        actionVoler = new PlayThief(new FakeListChooser<>(Ressource.WOOD), listPlayers);
       
        
        actionVoler.act(player2);

        assertEquals(0, player1.getRessourceAmount(Ressource.WOOD));
        assertEquals(10, player2.getRessourceAmount(Ressource.WOOD));
    }

    @Test
    void shouldThrowExceptionWhenPlayerHasNoThief() {
        player1.addRessource(Ressource.WOOD, 10);
        List<PlayerDemeter> listPlayers = new ArrayList<>();
        listPlayers.add(player1);
        listPlayers.add(player2);
        actionVoler = new PlayThief(new RandomListChooser<>(), listPlayers);
        
        assertThrows(NoMoreRessourcesException.class, () -> actionVoler.act(player1));
    }


    

    


}