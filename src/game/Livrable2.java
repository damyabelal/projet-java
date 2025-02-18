package game;
import game.tuile.*;

public class Livrable2 {
    public static void main (String[] args){
        if (args.length < 2) {
            System.out.println("you have to give two positive setting");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width < 10 || height < 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }
        Board board = new Board(width, height);
        board.display(); 
        placeBuildings(board);

    }
    private static void placeBuildings(Board board){

        Player player = new Player("player1");

        Earth portTile = new Forest();
        Earth farmTile = new Field();
        Earth exploitationTile = new Pasture();
        Earth armyTile = new Mountain();
        Earth campTile = new Mountain();                        

        Port port = new Port(portTile);
        Farm farm = new Farm(farmTile);
        Exploitation exploitation = new Exploitation(exploitationTile);
        Army army = new Army(armyTile,5);
        Camp camp = new Camp(campTile,20);
 
        // associer les building a leur tuile 
        portTile.setBuilding(port);
        farmTile.setBuilding(farm);
        exploitationTile.setBuilding(exploitation);
        armyTile.setBuilding(army);
        campTile.setBuilding(camp);

        
        /// display costs
        port.displayCost();
        farm.displayCost();
        exploitation.displayCost();
        army.displayCost();
        camp.displayCost();

        System.out.println("--------------------------------------");

        ///display collected resources 
        port.collectRessource(player);
        farm.collectRessource(player);
        exploitation.collectRessource(player);
        army.collectRessource(player);
        camp.collectRessource(player);




    }

    
}
