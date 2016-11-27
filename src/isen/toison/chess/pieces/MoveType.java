package isen.toison.chess.pieces;

/**
 * Created by isen on 18/10/2016.
 */
public enum MoveType {
    PION,
    TOWER,
    KNIGHT,
    FOUL,
    QUEEN,
    KING;


    @Override
    public String toString() {
        return super.toString().substring(0,1);
    }
}


