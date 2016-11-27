package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.MoveType.TOWER;

/**
 * Created by isen on 18/10/2016.
 */
public class Tower extends Piece{

    public Boolean alreadyMoved ;               //pour le rock qui reste à faire
    private int line;
    private int column;

    //constructor
    public Tower(Color color, Position position) {
        super(color, position);
        this.moveType=TOWER;
    }


    //finalpositions
    public List<Position> finalPositions(Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        line = 0;
        column = 1;
        isAbleToMove(finalPosition, currentBoard);
        line = 0;
        column = -1;
        isAbleToMove(finalPosition, currentBoard);
        line = 1;
        column = 0;
        isAbleToMove(finalPosition, currentBoard);
        line = -1;
        column = 0;
        isAbleToMove(finalPosition, currentBoard);
        return finalPositions;
    }

    //movehelp
    public List<Position> isAbleToMove(Position finalPosition, Board currentBoard) {
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
