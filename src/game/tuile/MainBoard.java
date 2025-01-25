package game.tuile;
import java.lang.Integer;

public class MainBoard{
  public static void  main(String[] args ){
    try {
    int height = Integer.parseUnsignedInt(args[0]);
    int width = Integer.parseUnsignedInt(args[1]);
    Board b= new Board(height,width);
    b.createBoard();
    b.display();
    
} catch (NumberFormatException e) {
    System.out.println("Input height and width.");
}
  }
}