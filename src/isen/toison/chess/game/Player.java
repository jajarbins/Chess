package isen.toison.chess.game;

import isen.toison.chess.pieces.Color;
import isen.toison.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isen on 18/10/2016.
 */
public class Player {
    public Color color;
    List<Piece> pieceOnBoardPlayer;


    //constructor
    public Player(Color color, Board board) {
        this.color = color;
        this.pieceOnBoardPlayer=board.pieceOnBoardByColor(this.color);
    }


}
