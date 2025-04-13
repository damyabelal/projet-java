package game;

import java.io.IOException;
import java.util.*;

import game.action.*;
import game.action.actionAres.AttackNeighboor;
import game.action.actionAres.BuildArmy;
import game.action.actionAres.BuySecretWeapon;
import game.action.actionAres.BuyWarriors;
import game.action.actionAres.DisplayWarriors;
import game.action.actionAres.UpgradeWithRessources;
import game.action.actionAres.UpgradeWithWarriors;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;
import game.listchooser.RandomListChooser;
import java.util.Random;



public class PlayerAres extends Player {

    private int warriors;
    private int secretWeapon;
    private List<Army> armies;
    private List<Camp> camps;
    private RandomListChooser<Action<PlayerAres>> lc;
    private List<Action<PlayerAres>> actionsAres;
    private int objective;
    
    
        // initializes a new PlayerAres with 30 warriors, a name, and zero secret weapons
    public PlayerAres(String name) {
        super(name);
        this.warriors = 30;
        this.secretWeapon = 0;
        this.armies = new ArrayList<>();
        this.camps = new ArrayList<>();
        this.actionsAres = new ArrayList<>();
        lc = new RandomListChooser<>(); 
    }

    /**
     * adds warriors to the army of this player
     * @param nb number of warriors to add
     */
    public void addWarriors(int nb) {
        this.warriors += nb;
    }

    /**
     * removes nb warriors from this player's army
     * @param nb number of warriors to remove
     * @exception NoMoreRessourcesException
     */
    public void removeWarriors(int nb) throws NoMoreRessourcesException {
        if ((this.warriors - nb) < 0) {
            throw new NoMoreRessourcesException("you have less than " + nb + " warriors");
        } else {
            this.warriors -= nb;
        }
    }

    /**
     * returns the number of warriors of this player
     * @return number of warriors belonging to this player
     */
    public int getWarriors() {
        return this.warriors;
    }

    /**
     * returns the number of secret weapons owned by this player
     * @return int
     */
    public int getNbSecretWeapon() {
        return this.secretWeapon;
    }

    /**
     * removes an army from the list of armies
     * @param army
     */
    public void removeArmy(Army army) {
        this.armies.remove(army);
        this.playerTiles.remove(army.getTuile()); 
    }

    /**
     * adds a secret weapon to this player
     */
    public void addSecretWeapon() {
        this.secretWeapon += 1;
    }

    /**
     * removes a secret weapon from this player
     */
    public void removeSecretWeapon() {
        this.secretWeapon -= 1;
    }

    /**
     * returns the list of armies of this player
     * @return List<Army>
     */
    public List<Army> getArmies() {
        return this.armies;
    }

    /**
     * adds an army to the list of armies of this player
     * @param army
     */
    public void addArmy(Army army) {
        this.armies.add(army);
        this.playerTiles.add(army.getTuile()); 
    }

    /**
     * returns the list of camps of this player
     * @return List<Camp>
     */
    public List<Camp> getCamps() {
        return this.camps;
    }

    /**
     * adds a camp to the list of camps of this player
     * @param camp
     */
    public void addCamp(Camp camp) {
        this.camps.add(camp);
        this.playerTiles.add(camp.getTuile()); 
    }

    /** return true of this player has envaded an entire island */
    public boolean didEnvadeIsland(){
        return true;// will do later
    
}

    public 

    /** returns all the possible objectives the player be asked to achieve in order to win the game */
    public HashMap<Integer,String> getGamesObjectives(){
        HashMap<Integer,String> objectifs=new HashMap<>();
        objectifs.put(0,"Envade an island.");
        objectifs.put(1,"Conquer a certain number of tiles.");
        objectifs.put(2,"Detain a certain Number of Warriors in the rank.");
        return objectifs;

    }
    /** selects randomly the objective for this player. Thus ,in order to win should accomplish this objectives */
    public AresGameObjectives getPlayersObjective(){
        Random random =new Random();
        int randomnumber=random.nextInt(2);
        // if the random objective is to invade an island 
        if (randomnumber==0){
           
            return new AresGameObjectives(1,0,0);
        }

        else if(randomnumber==1){
         int randomnumberoftiles=random.nextInt(10);
         return new AresGameObjectives(0,randomnumberoftiles,0);


        }
        else if(randomnumber==2){
        int randomnumberofwarriors=random.nextInt(20);
        return new AresGameObjectives(0,0,randomnumberofwarriors);
        }
       

    }
    //displays the 
    public void displayPlayersObjective(int cleobjectif){
         
    }

    public boolean isObjectiveAchieved(){
    if 

    }


    /**
     * presents the user with a list of actions that the player can do
     * @param board the game board
     */
    public void act(Board board) throws CantBuildException, NoMoreRessourcesException, IOException, InvalidChoiceException {
        if (this.actionsAres.isEmpty()) { 
            this.actionsAres = actionsPlayer(board);
        }
        Action<PlayerAres> action = lc.choose("Choose an action", this.actionsAres);
        if (action != null) {
            try {
                action.act(this);
            } catch (NoMoreRessourcesException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No action chosen");
        }
    }
    

    /**
     * returns a list of actions that the player Ares can do
     * @param board
     * @return List<Action<PlayerAres>>
     */
    private List<Action<PlayerAres>> actionsPlayer(Board board) {
        List<Action<PlayerAres>> aresActions = new ArrayList<>();

        aresActions.add(new BuildPort<PlayerAres>(this, board, new RandomListChooser<>()));
        aresActions.add(new ExchangeRessources<PlayerAres>(this, new RandomListChooser<>()));

        // add possible actions for player Ares
        
        aresActions.add(new BuildArmy(board, this, new RandomListChooser<>()));
        aresActions.add(new UpgradeWithRessources(this, new RandomListChooser<>()));
        aresActions.add(new UpgradeWithWarriors(this, new RandomListChooser<>(), new RandomListChooser<>()));
        aresActions.add(new BuySecretWeapon(this));
        aresActions.add(new BuyWarriors<PlayerAres>(this));
        aresActions.add(new DisplayWarriors(this, new RandomListChooser<>(), new RandomListChooser<>(), new RandomListChooser<>(), new RandomListChooser<>()));
        aresActions.add(new AttackNeighboor(this, null, new RandomListChooser<>()));

        return aresActions;
    }
}
