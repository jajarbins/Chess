package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.MoveType.QUEEN;

/**
 * Created by isen on 18/10/2016.
 */
public class Queen extends Piece {

    private int line;
    private int column;

    //constructor
    public Queen(Color color, Position position) {
        super(color, position);
        this.moveType=QUEEN;
    }

    //finalpositions
    public List<Position> finalPositions(Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        line = 0;
        column = 1;
        IsAbleToMove(finalPosition, currentBoard);
        line = 0;
        column = -1;
        IsAbleToMove(finalPosition, currentBoard);
        line = 1;
        column = 0;
        IsAbleToMove(finalPosition, currentBoard);
        line = -1;
        column = 0;
        IsAbleToMove(finalPosition, currentBoard);
        line = 1;
        column = 1;
        IsAbleToMove(finalPosition, currentBoard);
        line = -1;
        column = 1;
        IsAbleToMove(finalPosition, currentBoard);
        line = 1;
        column = -1;
        IsAbleToMove(finalPosition, currentBoard);
        line = -1;
        column = -1;
        IsAbleToMove(finalPosition, currentBoard);
        return finalPositions;
    }   //return the list of position where the Queen can go

    //movehelp
    public List<Position> IsAbleToMove(Position finalPosition, Board currentBoard) {
        while(true){

            if (line>0)   {finalPosition.line=position.line+line;}
            else{finalPosition.line=position.line-line;}


            if (column>0)   {finalPosition.column=position.column+column;}
            else{finalPosition.column=position.column-column;}

            if ( finalPosition.line>=0 || finalPosition.line<=7 ){  break;  }
            if( finalPosition.column>=0 || finalPosition.column<=7 ){  break;  }
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color==this.color )  {   break;  }
            finalPositions.add(finalPosition);
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color!=this.color )  {   break;  }

            if (line>0){line++;}                            //si on veut accrémenter la ligne, on met 1 dans l'argument line                                       //si on veut décrémenter la ligne, on met -1 dans l'argument line
            if (line<0){line--;}                             //si on veut décrémenter la ligne, on met -1 dans l'argument line                           //si on veut ne pas toucher à la ligne, put 0 dans l'argument line
            if (column>0){column++;}                          //de même pour column
            if (column<0){column--;}
        }
        return finalPositions;
    }
}
