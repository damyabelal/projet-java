    package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.action.Action;

import game.action.BuildArmy;
import game.action.BuildPort;
import game.action.BuySecretWeapon;
import game.action.BuyWarriors;
import game.action.ExchangeRessources;
import game.action.UpgradeArmy;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import listchooser.ListChooser;
import listchooser.RandomListChooser;


public class PlayerAres extends Player {

    private int warriors;
    private int secretWeapon;
    private List<Army> armies;
    private List<Camp> camps;
    private ListChooser<Action<PlayerAres>> lc = new RandomListChooser<>();
    private List<Action<PlayerAres>> actionsAres = new ArrayList<>();
    private Board board;

    // initializes a new PlayerAres with 30 warriors, a name, and zero secret weapons
    public PlayerAres(String name, Board board) {
        super(name);
        this.warriors = 30;
        this.secretWeapon = 0;
        this.armies = new ArrayList<>();
        this.camps = new ArrayList<>();
        this.actionsAres = this.actionsPlayer();
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
    }

    /**
     * presents the user with a list of actions that the player can do
     * @param board the game board
     */
    public void act(Board board) throws CantBuildException, NoMoreRessourcesException, IOException {
        List<Action<PlayerAres>> actions = actionsPlayer();
        Action<PlayerAres> action = lc.choose("Choose an action", actions);

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
    private List<Action<PlayerAres>> actionsPlayer() {
        List<Action<PlayerAres>> aresActions = new ArrayList<>();

        aresActions.add(new BuildPort<PlayerAres>(this, this.board));
        aresActions.add(new BuyWarriors<PlayerAres>(this));
        aresActions.add(new ExchangeRessources<PlayerAres>(this));

        // add possible actions for player Ares
        aresActions.add(new BuildArmy(this.board, this));
        aresActions.add(new UpgradeArmy(this));
        aresActions.add(new BuySecretWeapon(this));

        return aresActions;
    }
}
