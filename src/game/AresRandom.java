package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

public class AresRandom {

  public static void main(String[] args)
      throws IOException, InvalidChoiceException, CantBuildException, NoMoreRessourcesException {

    if (args.length < 3) {
      System.out.println("You have to give two positive settings and the number of player.");
      return;
    }

    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);
    int nbplayer = Integer.parseInt(args[2]);

    if (width < 10 || height < 10) {
      System.out.println("Width and height must be greater than 10.");
      return;
    }

    if (nbplayer < 2) {
      System.out.println("Number of players must be between 2 .");
      return;
    }

    System.out.println("--------------------");
    System.out.println("------ARES RANDOM---");
    System.out.println("--------------------");

    Board board = new Board(width, height);

    List<PlayerAres> players = new ArrayList<>();
    PlayerAres winner = null;

    // création des différents joueurs
    for (int i = 0; i < nbplayer; i++) {
      String name = "j" + (i + 1);
      players.add(new PlayerAres(name));
    }

    // Placement initail des armees
    System.out.println("Placement initial des armées (mode aléatoire) :");

    System.out.println("\n");
    System.out.println("TIME TO BUILD YOUR ARMIES !");
    System.out.println("\n");
    for (int j = 0; j < 2; j++) {
      for (PlayerAres p : players) {
        System.out.println(
            p.getName() + " (" + p.getResources() + "/ nb warriors: " + p.getWarriors() + ") build your armies");
        p.placeInitialArmy(board, new RandomListChooser<Earth>(), new RandomListChooser<Integer>());
      }
    }

    // initialisation des actions
    for (PlayerAres p : players) {
      p.createActions(board, 1);
    }

    // give a random objective for the player
    for (PlayerAres p : players){
      p.givePlayersObjective();
    } 

    while (winner == null) {
      for (PlayerAres p : players) {
        System.out.println(p.getName() + " (" + p.getResources() + ") tour !");
        p.collectRessources();
        board.display();

        p.createActions(board, 1);
        try {
          p.act(board, 1);
        } catch (NullPointerException e) {
          System.out.println("Action cancelled due to invalid or null choice.");

        }
        if (p.isObjectiveAchieved(board)) {
          winner = p;
          break;
        }
      }
    }

  }

}