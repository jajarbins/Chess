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
    private Board board;

    //constructor
    public Player(Color color, Board board) {
        this.color = color;
        this.board = board;
    }
    public List<Piece> getPieceOnBoardPlayer() {
        return board.pieceOnBoardByColor(this.color);
    }

}
