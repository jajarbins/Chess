package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;
import isen.toison.chess.game.Cell;


import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.Color.White;
import static isen.toison.chess.pieces.MoveType.PION;

/**
 * Created by isen on 18/10/2016.
 */
public class Pion extends Piece {
    public Boolean AlreadyMoved;
    public int numPion;
    //constructor
    public Pion(Color color, Position position, int numPion) {
        super(color, position);
        this.moveType = PION;
        this.numPion=numPion;
    }


    //finalpositions
    public List<Position> finalPositions(Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        finalPosition.line = position.line;
        if (this.color == White) {
            if (this.AlreadyMoved = Boolean.FALSE) {
                IsAbleToMovePionHelp2 (1, position, finalPosition, currentBoard, finalPositions);
                IsAbleToMovePionHelp2 (2, position, finalPosition, currentBoard, finalPositions);
                this.AlreadyMoved = Boolean.TRUE;
                return (finalPositions);
            } else {
                IsAbleToMovePionHelp2 (1, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            }
        } else {
            if (this.AlreadyMoved = Boolean.FALSE) {
                IsAbleToMovePionHelp2 (-1, position, finalPosition, currentBoard, finalPositions);
                IsAbleToMovePionHelp2 (-2, position, finalPosition, currentBoard, finalPositions);
                this.AlreadyMoved = Boolean.TRUE;
                return (finalPositions);
            } else {
                IsAbleToMovePionHelp2 (-1, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            }
        }
    }

    //moveHelp
    public List<Position> IsAbleToMovePionHelp (Position position, Position finalPosition,
                                                 Board currentBoard, List < Position > finalPositions){
        if (currentBoard.boardMatrix[position.column - 1][position.line + 1].getPiece().color != this.color)
        {finalPositions.add(finalPosition);}
        if( currentBoard.boardMatrix[position.column + 1][position.line + 1].getPiece().color != this.color)
        {finalPositions.add(finalPosition);}
        if (finalPosition.line >= 0 || finalPosition.line <= 7) {
            if (finalPosition.column >= 0 || finalPosition.column <= 7) {
                if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].isOccupied() == false)
                {finalPositions.add(finalPosition);}
            }
        }
        return finalPositions;
    } //ne fonctionne peut être pas
    public List<Position> IsAbleToMovePionHelp2 (int i, Position position, Position finalPosition,
                                                 Board currentBoard, List < Position > finalPositions) {
        finalPosition.column = position.column + i;
        IsAbleToMovePionHelp(position, finalPosition, currentBoard, finalPositions);
        return (finalPositions);
    }
}

