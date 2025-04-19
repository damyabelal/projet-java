package game;

import game.action.Action;
import game.tuile.Field;
import game.util.Position;
import game.tuile.Earth;

import java.util.List;

public class PlayerAresDebugMain {
        public static void main(String[] args) {
            // Création du joueur
            PlayerAres player = new PlayerAres("DébugAres");
    
            // Création du plateau
            Board board = new Board(5, 5);
    
            // Remplir le board de tuiles Earth
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board.put(new Field(), new Position(i, j));
                }
            }
    
            // PAS de ressources ajoutées ici → on veut tester le comportement sans ressource
    
            // Génération des actions en mode aléatoire (1)
            player.createActions(board, 1);
    
            // Affichage des actions générées
            List<Action<PlayerAres>> actions = player.getActionsAres();
            System.out.println("Nombre d'actions générées : " + actions.size());
    
            for (Action<PlayerAres> action : actions) {
                System.out.println("→ " + action.getClass().getSimpleName());
            }
        }
}
