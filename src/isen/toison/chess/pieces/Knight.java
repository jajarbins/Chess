package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.MoveType.KNIGHT;

/**
 * Created by isen on 18/10/2016.
 */
public class Knight extends Piece {

    //constructor
    public Knight(Color color, Position position) {
        super(color, position);
        this.moveType=KNIGHT;
    }

    //finalpositions
    public List<Position> finalPositions(Position position, Board currentBoard){
        Position finalPosition=new Position();
        List<Position> finalPositions = new ArrayList<>();
        int l;          //l pour ligne
        int c=-2;      //c pour colone
        while(c<=2){
            switch(c){
                case -2 :
                    IsAbleToMoveKnightHelp(1, c, finalPosition, currentBoard, finalPositions);
                    break;
                case -1 :
                    IsAbleToMoveKnightHelp(2, c, finalPosition, currentBoard, finalPositions);
                    break;
                case 0 :
                    c++;
                    break;
                case 1 :
                    IsAbleToMoveKnightHelp(2, c, finalPosition, currentBoard, finalPositions);
                    break;
                case 2 :
                    IsAbleToMoveKnightHelp(1, c, finalPosition, currentBoard, finalPositions);
                    break;
            }
            c++;
        }
        return finalPositions;
    }

    //movehelp
    public List<Position> IsAbleToMoveKingHelp(Position finalPosition, Board currentBoard, List<Position> finalPositions){
        if (finalPosition.line>=0 || finalPosition.line<=7) {
            if (finalPosition.column >= 0 || finalPosition.column <= 7) {
                if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].isOccupied() == false
                        || currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color != this.color) {
                    finalPositions.add(finalPosition);
                }
            }
        }
        return finalPositions;
    }

    public List<Position> IsAbleToMoveKnightHelp(int i, int c, Position finalPosition, Board currentBoard, List<Position> finalPositions){
        finalPosition.line=position.line-i;
        finalPosition.column=position.column+c;
        IsAbleToMoveKingHelp(finalPosition, currentBoard, finalPositions);
        finalPosition.line=position.line+i;
        IsAbleToMoveKingHelp(finalPosition, currentBoard, finalPositions);
        return finalPositions;
    }
}

