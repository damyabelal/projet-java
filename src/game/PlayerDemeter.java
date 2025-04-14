package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.*;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;
import game.listchooser.InteractiveListChooser;
import game.listchooser.ListChooser;
import game.listchooser.RandomListChooser; 
import game.action.*;
import game.action.actionDemeter.BuildFarm;
import game.action.actionDemeter.BuyThief;
import game.action.actionDemeter.ExchangeRessourcesPort;
import game.action.actionDemeter.PlayThief;
import game.action.actionDemeter.UpgradeFarm;

public class PlayerDemeter extends Player{

    private int points;
    private int nbThief;
    private List<Farm> farms;
    private List<Exploitation> exploitations;
    private List<Action<PlayerDemeter>> actionsDemeter;


    /** 
     * creates a demeter player with a name and number of points and number of thiefs that he has
     * @param name  name of the player
     */ 

    public PlayerDemeter(String name) {
            super(name);
            this.points = 0;
            this.nbThief = 0; 
            this.farms = new ArrayList<>();
            this.exploitations = new ArrayList<>();
            this.actionsDemeter = new ArrayList<>();
    }

    /**
     * gets the number of points that the demeter player has
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * gets the number of thief that the demeter player has
     */
    public int getNbThief(){
        return this.nbThief;
    }

    /**
     * adds the given number of points to the demeter player
     * @param nb int number of points to add
     */
    public void addPoints(int nb){
        this.points += nb;
    }
    /**
     * adds a  thief to the  demeter player
     * @param nb int number of thief to add
     */
    public void addThief(){
        this.nbThief += 1 ;
    }

    /**
     * gets the farms of the demeter player
     * @return List<Farm> the farms of the demeter player
     */
    public List<Farm> getFarms(){
        return this.farms;
    }

    /**
     * adds  a farm to the demeter player
     * @param farm the farm to add
     */
    public void addFarm(Farm farm){
        this.farms.add(farm);
        this.playerTiles.add(farm.getTuile()); 
    }

    /**
     * removes a farm from the demeter player
     * @param farm the farm to remove
     */
    public void removeFarm(Farm farm){
        this.farms.remove(farm);
        this.playerTiles.remove(farm.getTuile()); 
    }


    /**
     * check if the demeter player has a port in his tiles
     * @return boolean true if the demeter player has a port in his tiles
     */
    public boolean hasPort(){
        return !this.getPlayerPorts().isEmpty();
    }


    /**
     * get the exploitations of the demeter player
     * @return List<Exploitation> the exploitations of the demeter player
     */
    public List<Exploitation> getExploitations(){
        return this.exploitations;
    }

    /**
     * adds an exploitation to the demeter player
     * @param exploitation the exploitation to add
     */
    public void addExploitation(Exploitation exploitation){
        this.exploitations.add(exploitation);
        this.playerTiles.add(exploitation.getTuile()); 
    }

    /**
     * collect all the ressources from the differents building 
     */
    public void collectRessources(){
        for ( Farm farm : this.farms){
            this.addRessource(farm.getTuileRessource(), 1);
        }
        for (Exploitation exploitation: this.exploitations){
            this.addRessource(exploitation.getTuileRessource(), 2);
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
        ListChooser<Farm> lcFarm=null; 
        ListChooser<Ressource> lcRessource=null;  

        if (this.actionsDemeter.isEmpty()) { 
            if (option==0){
                lcEarth= new InteractiveListChooser<>(); 
                lcFarm= new InteractiveListChooser<>();
                lcRessource= new InteractiveListChooser<>(); 
            }
            else{
                lcEarth= new RandomListChooser<>(); 
                lcFarm= new RandomListChooser<>();
                lcRessource= new RandomListChooser<>(); 
            }
            this.actionsDemeter = actionsPlayer(board, lcEarth, lcRessource, lcFarm);
        }

    }

    /**
     * excute the action of the demeter player
     * @param board
     * @param option if option is 0 then we create a interactive actions List, otherwise the actions will be automatic
     * @throws IOException
     */
    public void act(Board board, int option) throws IOException, InvalidChoiceException {
        ListChooser<Action<PlayerDemeter>> lc= null;

        if (option==0){
            lc= new InteractiveListChooser<>(); 
        }
        else{
            lc= new RandomListChooser<>(); 
        }

        Action<PlayerDemeter> demeterAction = lc.choose("Choose an action", this.actionsDemeter);
        if (demeterAction != null) {
            try {
                demeterAction.act(this);
            } catch (NoMoreRessourcesException | CantBuildException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No action chosen");
        }
    }

    /**
     * returns a list of actions that the demeter player can do
     * @param board
     * @return List<Action<PlayerDemeter>> the list of actions that the demeter player can do
     */
    private List<Action<PlayerDemeter>> actionsPlayer(Board board, ListChooser<Earth> lcEarth, ListChooser<Ressource> lcRessource, ListChooser<Farm> lcFarm){
        List<Action<PlayerDemeter>> actionsDemeter = new ArrayList<>();

        BuildPort<PlayerDemeter> buildPort = new BuildPort<>(this, board, lcEarth);
        if (buildPort.hasEnoughRessources()) {
            actionsDemeter.add(buildPort);
        }

        ExchangeRessources<PlayerDemeter> exchange = new ExchangeRessources<>(this, lcRessource);
        if (exchange.hasEnoughRessources()) {
            actionsDemeter.add(exchange);
        }


        // add possibles actions for player Demeter
        UpgradeFarm upgradeFarm = new UpgradeFarm(this, lcFarm);
        if (!this.getFarms().isEmpty() && upgradeFarm.hasEnoughRessources()) {
            actionsDemeter.add(upgradeFarm);
        }


        

        actionsDemeter.add(new ExchangeRessourcesPort(this, lcRessource));
        actionsDemeter.add(new BuildFarm(board, this, lcEarth));
        actionsDemeter.add(new BuyThief(this));
        actionsDemeter.add(new PlayThief(null, null));
        return actionsDemeter;
    }

    /**
     * 
     * @param board
     */
    public void placeInitialFarm(Board board){
        List<Earth> buildableTiles = board.buildableTiles();
        ListChooser<Earth> chooser = new InteractiveListChooser<>();
        Earth chosenTile = chooser.choose("Choose a tile to place your initial farm", buildableTiles);

        Farm farm = new Farm(chosenTile, this);
        chosenTile.setBuilding(farm);
        this.addFarm(farm);
        this.playerTiles.add(chosenTile);
    }




    /**
     * 
     * @param board
     */
    public void placeInitialFarmRandom(Board board){
        List<Earth> buildableTiles = board.buildableTiles();
        ListChooser<Earth> chooser = new RandomListChooser<>();
        Earth chosenTile = chooser.choose("Choose a tile to place your initial farm", buildableTiles);

        Farm farm = new Farm(chosenTile, this);
        chosenTile.setBuilding(farm);
        this.addFarm(farm);
        this.playerTiles.add(chosenTile);
    }
}