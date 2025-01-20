package game.util;

/**
 * enum qui represente les directions possibles pour verifier l'encadrement des tuiles
 */
public enum Direction {
    N(1, 0),   // haut
    S(-1, 0),  // bas
    O(0, -1),  // gauche
    E(0, 1);   // droit

    private final int dx;
    private final int dy; 

    /**
     * constructeur pour definir les déplacements en x et y
     *
     * @param dx le deplacement en x
     * @param dy le deplacement en y
     */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * retourne le deplacement en x pour cette direction
     *
     * @return dx le deplacement en x
     */
    public int getDx() {
        return this.dx;
    }

    /**
     * retourne le deplacement en y pour cette direction
     *
     * @return dy Le déplacement en y
     */
    public int getDy() {
        return this.dy;
    }
}