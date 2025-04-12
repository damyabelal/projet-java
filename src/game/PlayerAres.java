package game;

import java.io.IOException;
import java.util.*;

import game.action.*;
import game.action.actionAres.*;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.*;
import game.listchooser.*;


public class PlayerAres extends Player {

    private int warriors;
    private int secretWeapon;
    private List<Army> armies;
    private List<Camp> camps;
    private List<Action<PlayerAres>> actionsAres;
    
    
        // initializes a new PlayerAres with 30 warriors, a name, and zero secret weapons
    public PlayerAres(String name) {
        super(name);
        this.warriors = 30;
        this.secretWeapon = 0;
        this.armies = new ArrayList<>();
        this.camps = new ArrayList<>();
        this.actionsAres = new ArrayList<>();
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

    /**
     * collect all the ressources from the differents building 
     */
    public void collectRessources(){
        for ( Army army : this.armies){
            this.addRessource(army.getTuileRessource(), 1);
        }
        for (Camp camp: this.camps){
            this.addRessource(camp.getTuileRessource(), 2);
        }
    }

    /**
     * fill the actionsDemeter attributes with all the actions for this game
     * if option is 0 the actions will be in interactive mode, random otherwise
     * @param board
     * @param option
     */
    public void createActions(Board board, int option){
        ListChooser<Earth> lcEarth= null; 
        ListChooser<Army> lcArmy= null; 
        ListChooser<Ressource> lcRessource=null;  
        ListChooser<Integer> lcInt= null; 
        ListChooser<String> lcString = null; 
        ListChooser<Camp> lcCamp=null; 
        ListChooser<PlayerAres> lcPlayer=null; 

        if (this.actionsAres.isEmpty()) { 
            if (option==0){
                lcEarth= new InteractiveListChooser<>(); 
                lcArmy= new InteractiveListChooser<>();
                lcRessource= new InteractiveListChooser<>();
                lcInt= new InteractiveListChooser<>(); 
                lcString = new InteractiveListChooser<>(); 
                lcCamp=new InteractiveListChooser<>(); 
                lcPlayer=new InteractiveListChooser<>(); 
            }
            else{
                lcEarth= new RandomListChooser<>(); 
                lcArmy= new RandomListChooser<>();
                lcRessource= new RandomListChooser<>(); 
                lcInt= new RandomListChooser<>(); 
                lcString = new RandomListChooser<>(); 
                lcCamp=new RandomListChooser<>(); 
                lcPlayer=new RandomListChooser<>(); 
            }
            this.actionsAres = actionsPlayer(board, lcEarth, lcRessource, lcArmy, lcInt,lcString, lcCamp, lcPlayer);
        }

    }

    /**
     * excute the action of the demeter player
     * @param board
     * @param option if option is 0 then we create a interactive actions List, otherwise the actions will be automatic
     * @throws IOException
     */
    public void act(Board board, int option) throws IOException, InvalidChoiceException {
        ListChooser<Action<PlayerAres>> lc= null;

        if (option==0){
            lc= new InteractiveListChooser<>(); 
        }
        else{
            lc= new RandomListChooser<>(); 
        }

        Action<PlayerAres> aresAction = lc.choose("Choose an action", this.actionsAres);
        if (actionsAres != null) {
            try {
                aresAction.act(this);
            } catch (NoMoreRessourcesException | CantBuildException e) {
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
    private List<Action<PlayerAres>> actionsPlayer(Board board, ListChooser<Earth> lcEarth, ListChooser<Ressource> lcRessource, ListChooser<Army> lcArmy, ListChooser<Integer> lcInt, ListChooser<String> lcString, ListChooser<Camp> lcCamp, ListChooser<PlayerAres> lcPlayer) {
        List<Action<PlayerAres>> aresActions = new ArrayList<>();

        aresActions.add(new BuildPort<PlayerAres>(this, board, lcEarth));
        aresActions.add(new ExchangeRessources<PlayerAres>(this, lcRessource));

        // add possible actions for player Ares
        
        aresActions.add(new BuildArmy(board, this, lcEarth));
        aresActions.add(new UpgradeWithRessources(this, lcArmy));
        aresActions.add(new UpgradeWithWarriors(this, lcInt, lcArmy));
        aresActions.add(new BuySecretWeapon(this));
        aresActions.add(new BuyWarriors<PlayerAres>(this));
        aresActions.add(new DisplayWarriors(this, lcInt, lcString, lcArmy, lcCamp));
        aresActions.add(new AttackNeighboor(this, null, lcPlayer));

        return aresActions;
    }
}
