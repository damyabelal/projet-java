package game;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.action.Action;
import game.tuile.Earth;
import game.tuile.Field;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.NoMoreRessourcesException;
import game.util.Position;

public class PlayerAresTest {
    
    private PlayerAres player;

    @BeforeEach
    // Create a player and a field tile
    void setUp() {
        player = new PlayerAres("TestPlayer");
    }

    @Test
    void testPlayerAddWarriors() {
        player.addWarriors(10);
        assertEquals(40, player.getWarriors());
    }
    /// test the player removing warriors
    @Test
    void testRemoveWarriors() throws NoMoreRessourcesException {
        player.removeWarriors(10);
        assertEquals(20, player.getWarriors());
    }

    
    @Test // it should throw an exception
    void TestNoWarriorsToRemove() throws NoMoreRessourcesException{
        assertEquals(30, player.getWarriors());
        player.removeWarriors(30); // removing the initial 30 warriors the player has
        assertEquals(0, player.getWarriors());
        assertThrows(NoMoreRessourcesException.class ,()->{player.removeWarriors(1);});//removing a warrior from player having 0 warriors should throw an exception
        

    }
    @Test
    void testSecretWeaponAddRemove() {
        assertEquals(0, player.getNbSecretWeapon());

        player.addSecretWeapon();
        assertEquals(1, player.getNbSecretWeapon());

        player.removeSecretWeapon();
        assertEquals(0, player.getNbSecretWeapon());
    }


    @Test
    void testAddArmy() throws Exception {
        Army army = new Army(null, 3, player);
        player.addArmy(army);

        assertTrue(player.getArmies().contains(army));
        assertTrue(player.getTiles().contains(army.getTuile()));
    }



    @Test
    void testRemoveArmy() throws Exception{
        Army army = new Army(null, 2, player);
        player.addArmy(army);
        assertEquals(1, player.getArmies().size());

        player.removeArmy(army);
        assertEquals(0, player.getArmies().size());
        assertFalse(player.getTiles().contains(army.getTuile()));
    }

    @Test
    void testAddCamp()throws Exception {
        Camp camp = new Camp(null, 5, player);
        player.addCamp(camp);

        assertTrue(player.getCamps().contains(camp));
        assertTrue(player.getTiles().contains(camp.getTuile()));
    }

    
    // tets si  givePlayersObjective() cree bien un objectif
    @Test
    void testGivePlayersObjectives(){
        
        player.givePlayersObjective();
        assertNotNull(player.getObjective());
    }

    @Test
    void testHasConqueredTilesObjective() throws Exception {
        Board board = new Board(5, 5);
        // On force un objectif "conquerir au moins 1 tuile"
        player.setObjective( new AresGameObjectives(AresGameObjectives.ObjectiveType.CONQUER_TILES, 1));

        // On ajoute une armee sur un etuile (null ici car on ne teste que la quantité)
        Army army = new Army(null, 1, player);
        player.addArmy(army);

        // On verifie que l’objectif est atteint
        assertTrue(player.isObjectiveAchieved(board));
    }



   
    @Test
    void testDidDetainWarriorsObjectiveAchieved() throws Exception {
        Board board = new Board(5, 5);
        // On fixe un objectif : détenir au moins 3 guerriers
        player.setObjective(new AresGameObjectives(AresGameObjectives.ObjectiveType.DETAIN_WARRIORS, 3));

        // On ajoute une armée contenant 3 guerriers
        Army army = new Army(null, 3, player);
        player.addArmy(army);

        // On vérifie que l'objectif est bien atteint
        assertTrue(player.isObjectiveAchieved(board));
    }



    @Test
    void testCollectRessources() throws Exception {
        Earth e2 = new Field();
        Earth e1 = new Field();


        // Creation d'une armee et d'un camp fictifs sur des tuiles sans position
        Army army = new Army(e1, 1, player);
        Camp camp = new Camp(e2, 2, player);


        // associater le batimlent a une tuile
        e1.setBuilding(army);
        e2.setBuilding(camp);


        // Ajout au joueur
        player.addArmy(army);
        player.addCamp(camp);

        // Calcul du total de ressources avant
        int before = 0;
        for (Integer val : player.getResources().values()) {
            before += val;
        }

        // Appel de la methode
        player.collectRessources();

        // Calcul du total apres
        int after = 0;
        for (Integer val : player.getResources().values()) {
            after += val;
        }
    

        // On s’attend e +1 (armee) +2 (camp) = +3
        assertEquals(3, after - before);
    }

    @Test
    void testDidEnvadeIsland() throws Exception {

        Board board = new Board(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.put(new game.tuile.Sea(), new Position(i, j));
            }
        }

        // On place 4 tuiles Earth connectees , une ile
        Earth e1 = new Field();
        Earth e2 = new Field();
        Earth e3 = new Field();
        Earth e4 = new Field();

        board.put(e1, new Position(1, 1));
        board.put(e2, new Position(1, 2));
        board.put(e3, new Position(2, 1));
        board.put(e4, new Position(2, 2));

        
        player.getTiles().add(e1);
        player.getTiles().add(e2);
        player.getTiles().add(e3);
        player.getTiles().add(e4);


       
        board.islands = board.findIslands();

        // On lui donne l’objectif
        player.setObjective(new AresGameObjectives(AresGameObjectives.ObjectiveType.INVADE_ISLANDS, 1));
        assertTrue(player.isObjectiveAchieved(board));
    }




    @Test
    void testCreateActions() throws Exception {
        Board board = new Board(5, 5);

        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board.put(new Field(), new Position(i, j));
            }
        }

        
        player.addRessource(Ressource.WOOD, 5);
        player.addRessource(Ressource.SHEEP, 5);
        player.addRessource(Ressource.WEALTH, 5);

        player.createActions(board, 1);
        
        assertNotNull(player.getActionsAres());
        assertFalse(player.getActionsAres().isEmpty());
    
    
    
    }

    @Test
    void testCreateActionsWithoutRessources() throws Exception {
        Board board = new Board(5, 5);

        // Remplir le plateau de tuiles Earth valides
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board.put(new Field(), new Position(i, j));
            }
        }

        
        player.createActions(board, 1);
        List<Action<PlayerAres>> actions = player.getActionsAres();
        assertEquals(3, actions.size()); 
        boolean hasDisplayWarriors = false;
        boolean hasAttackNeighboor = false;
        
        for (Action<PlayerAres> action : actions) {
            if (action instanceof game.action.actionAres.DisplayWarriors) {
                hasDisplayWarriors = true;
            }
            if (action instanceof game.action.actionAres.AttackNeighboor) {
                hasAttackNeighboor = true;
            }
        }
        
        assertTrue(hasDisplayWarriors);
        assertTrue(hasAttackNeighboor);

    }
       

    @Test
    void placeInitialArmyTest() throws CantBuildException{

       Board board=new Board(5, 5);
       // before placing an army randomly for the player , the player has no army
       assertTrue( player.getArmies().isEmpty());
       //placing an army randomly on the board
       player.placeInitialArmyRandom(board);
        // afterwards the player possess one army
        assertTrue( player.getArmies().size()==1);

       

    }



    






















}
