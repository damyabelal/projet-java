package game.action;

import java.util.List;
import java.util.Random;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import listchooser.RandomListChooser;

public class AttackNeighboor extends ActionManager implements Action<PlayerAres>{

    public RandomListChooser<PlayerAres> lc;
        public List<PlayerAres> ennemies;
    
        /**
         * constructor of AttackNeighboor
         * @param player the player who attack
         * @param ennemies a list of all the neighboor ennemies
         */ 
        public AttackNeighboor(PlayerAres player, List<PlayerAres> ennemies){
            super(player); 
            this.ennemies= ennemies;
            lc= new RandomListChooser<PlayerAres>(); 
        }
    
        /**
         * ask the player which neighboor he wants to attack
         * @return the player who is going to be attack
         */
        public PlayerAres askNeighboor(){
            return lc.choose("Who do you want to attack", this.ennemies);
    }

    /**
     * return how much dice a player can throw based on the number of warriors he have 
     * and if he have a secret weapon
     * @param player
     * @return the number of dice
     */
    public Integer howMuchDice(PlayerAres player){
        int nbWarriors= player.getWarriors();
        int res= 0;
        if (player.getNbSecretWeapon()> 0){
            player.removeSecretWeapon();
            res += 1;
        }
        if (nbWarriors>1 && nbWarriors<4){
            res+= 1;
        }
        else{
            if(nbWarriors<8){
                res += 2;
            }
            else{
                res += 3;
            }
        }
        return res; 
    }

    /**
     * return the sum of the dices thrown by the player
     * @param numberDices
     * @return the result of the dices
     */
    public Integer dicesResult(Integer numberDices){
        Random dice= new Random();
        Integer result= 0; 
        Integer i= numberDices;
        while (i>0){
            result += dice.nextInt(6)+1;;
        }
        return result;
    }

    public void act(PlayerAres player) throws NoMoreRessourcesException {
        PlayerAres ennemy= askNeighboor();
        // la somme des dés de chaque joueur
        System.out.println(player.getName() + " VS " + ennemy.getName());
        Integer ennemyRes= dicesResult(howMuchDice(ennemy));
        Integer playerRes= dicesResult(howMuchDice(player));
        PlayerAres loser;
        if (ennemyRes< playerRes){
            loser = ennemy;
        }
        else{
            loser= player;
        }
        //je sais pas comment on va faire pour savoir quel tuile on attaque je pense que 
        // mon code est confus et à revoir 
        loser.removeWarriors(1);
    }
    
}
