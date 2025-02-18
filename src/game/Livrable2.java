package game;

public class Livrable2 {
    public static void main (string[] args){
        if (args.length < 2) {
            System.out.println("you have to give two positive setting");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width <= 10 || height <= 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }

        Board board = new Board(width, height);
        board.display(); // affichage au debut du jeu

        placeBuilding(board);


        private void placeBuilding(Board board){


        }

        




    }

    
}
