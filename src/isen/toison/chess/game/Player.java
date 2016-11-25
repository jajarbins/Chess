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
    public Piece[] piecesPlayer;


    //constructor
    public Player(Color color, List<Piece> pieceOnBoardPlayer) {
        this.color = color;
        this.pieceOnBoardPlayer=pieceOnBoardPlayer;
    }


    //liste piece : All / White /Black
    public List<Piece> pieceOnBoard (Board currentBoard){
        int i; int j;
        List<Piece> pieceOnBoard = new ArrayList<>();
        for (i=0; i<=7; i++){
            for (j=0; j<=7; j++){
                if (currentBoard.boardMatrix[i][j].isOccupied()){
                    pieceOnBoard.add(currentBoard.boardMatrix[i][j].getPiece());
                }
            }
        }
        return pieceOnBoard;
    }
    public List<Piece> pieceOnBoardWhite ( List<Piece> pieceOnBoardW){
        for (int i=0; i<pieceOnBoardW.size(); i++){
            if (pieceOnBoardW.get(i).getColor() == Color.Black)
                pieceOnBoardW.remove(i);
        }
        return pieceOnBoardW;
    }
    public List<Piece> pieceOnBoardBlack (List<Piece> pieceOnBoardB){
        for (int i=0; i<pieceOnBoardB.size(); i++){
            if (pieceOnBoardB.get(i).getColor() == Color.White)
                pieceOnBoardB.remove(i);
        }
        return pieceOnBoardB;
    }
}
