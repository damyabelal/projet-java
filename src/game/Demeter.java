package game;

public class Demeter {

    public static void main(String[] args){
        if (args.length < 3) {
            System.out.println("You have to give two positive settings and the number of player.");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width < 10 || height < 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }
        System.out.println("---------------------");
        System.out.println("-------DEMETER-------");
        System.out.println("---------------------");
        System.out.println("\n"); 

        Board board = new Board(width, height);


    }
    
    
}
