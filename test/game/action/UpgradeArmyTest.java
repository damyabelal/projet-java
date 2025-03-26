package game.action;

import game.*;
import game.tuile.Forest;
import game.tuile.Ressource;
import game.tuile.building.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpgradeArmyTest {

    private PlayerAres player;
    private UpgradeArmy upgradeArmyAction;

    @BeforeEach
    void setUp() throws NoMoreRessourcesException, CantBuildException {
        player = new PlayerAres("cool");
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 3);
        Army army = new Army(new Forest(), 5, player);
        player.addArmy(army);
        upgradeArmyAction = new UpgradeArmy(player);
        }

        @Test
        void testUpgradeArmyWithResources() throws NoMoreRessourcesException, CantBuildException {
        assertEquals(0, player.getCamps().size());
        
        upgradeArmyAction = new UpgradeArmy(player) {
            @Override
            public String askUpgradeMethod() {
                return "resources";
            }

           // @Override
           // public Army askArmy() {
                // je sais pas c'est quoi l eprobleme 
               // return player.getArmies().get(0);
           // }
        };
        upgradeArmyAction.act(player);
        assertEquals(1, player.getCamps().size());
        assertTrue(player.getCamps().get(0) instanceof Camp);
    }

    @Test
    void testUpgradeArmyNotEnoughResources() throws CantBuildException, NoMoreRessourcesException{
        Army army = new Army(null, 5, player);
        player.addArmy(army);

        upgradeArmyAction = new UpgradeArmy(player) {
            @Override
            public String askUpgradeMethod() {
                return "resources";
            }

            @Override
            public Army askArmy() {
                return army;
            }
        };
        
        player.removeRessource(Ressource.WOOD, 2);
        player.removeRessource(Ressource.ORE, 3);
        assertThrows(NoMoreRessourcesException.class, () -> upgradeArmyAction.act(player));
    }

    @Test
    void testUpgradeArmyWithWarriors() throws NoMoreRessourcesException, CantBuildException {

        Army army = player.getArmies().get(0);
        player.addWarriors(4);

        upgradeArmyAction = new UpgradeArmy(player){
        
       
        public String askUpgradeMethod() {
            return "warriors"; 
        }

        
        public Army askArmy() {
            return army; 
        } 
        };

        assertEquals(5, army.getNbWarriors());
        upgradeArmyAction.act(player);
        
        assertNotNull(player.getCamps().get(0));

    
        assertEquals(1, player.getCamps().size());  
        Camp camp = player.getCamps().get(0);
        assertNotNull(camp);
        assertTrue(camp instanceof Camp);
        assertTrue(camp.getNbWarriors() >= 5);

        }

        @Test
        void testUpgradeArmyWithNotEnoughWarriors() throws CantBuildException {
        Army army = new Army(new Forest(), 4, player);
        player.addArmy(army);

        upgradeArmyAction = new UpgradeArmy(player) {
            @Override
            public String askUpgradeMethod() {
                return "warriors";
            }

            @Override
            public Army askArmy() {
                return army;
            }

            @Override
            public int askNumberOfWarriors(int max) {
                return 2; 
            }
        };
        assertThrows(CantBuildException.class, () -> upgradeArmyAction.act(player));
        }
    }
