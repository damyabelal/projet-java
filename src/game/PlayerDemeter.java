package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.tuile.building.*;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

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
    private RandomListChooser <Action<PlayerDemeter>> lc;
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
            lc = new RandomListChooser<>(); 
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
     * excute the action of the demeter player
     * @param board
     * @throws IOException
     */
    public void act(Board board) throws IOException, InvalidChoiceException {
        if (this.actionsDemeter.isEmpty()) { 
            this.actionsDemeter = actionsPlayer(board);
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
    private List<Action<PlayerDemeter>> actionsPlayer(Board board){
        List<Action<PlayerDemeter>> actionsDemeter = new ArrayList<>();

        actionsDemeter.add(new BuildPort<PlayerDemeter>(this, board, new RandomListChooser<>()));
        actionsDemeter.add(new ExchangeRessources<PlayerDemeter>(this, new RandomListChooser<>()));

        // add possibles actions for player Demeter
        actionsDemeter.add(new UpgradeFarm(this, new RandomListChooser<>()));
        actionsDemeter.add(new ExchangeRessourcesPort(this, new RandomListChooser<>()));
        actionsDemeter.add(new BuildFarm(board, this, new RandomListChooser<>()));
        actionsDemeter.add(new BuyThief(this));
        actionsDemeter.add(new PlayThief(null, null));
        return actionsDemeter;
    }
}