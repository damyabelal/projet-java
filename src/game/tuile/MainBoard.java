package game.tuile;

import java.lang.Integer;

public class MainBoard {
  public static void main(String[] args) {
    try {

      if (args.length < 2) {
        System.out.println("vous devez fournir les dimensions de votre plateau");
        return;
      }

      int height = Integer.parseUnsignedInt(args[0]);
      int width = Integer.parseUnsignedInt(args[1]);

      if (height <= 0 || width <= 0) {
        System.out.println("les dimensions doivent etre positives");
        return;
      }

      Board b = new Board(height, width);
      b.createBoard();
      b.display();

    } catch (NumberFormatException e) {
      System.out.println("vous devez fournir des dimensions positives");
    }
  }
}
