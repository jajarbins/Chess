package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.Color.White;
import static isen.toison.chess.pieces.MoveType.*;

/**
 * Created by isen on 18/10/2016.
 */

public abstract class Piece {

    public Color color;
    public MoveType moveType;
    public boolean isOnBoard;
    public Position position;
    public Boolean alreadyMoved ;
    public int numberPion;

    public Piece(Color color, Position position) {
    }

    // constructors


    //piece move choice
    public List<Position> PieceMoveChoice (Piece piece, Board currentBoard){
        switch (piece.moveType){
            case PION:
                return finalPositionsPion( piece.position, currentBoard);
            case TOWER:
                return finalPositionsTower ( piece.position, currentBoard);
            case KNIGHT:
                return finalPositionsKnight ( piece.position, currentBoard);
            case FOUL:
                return finalPositionsFoul ( piece.position, currentBoard);
            case QUEEN:
                return finalPositionsQueen ( piece.position, currentBoard);
            case KING:
                return finalPositionsKing ( piece.position, currentBoard);
            default :
                List<Position> lPositionError = new ArrayList<>();           //histoire de retourner quelque chose s'il y a un pb.
                lPositionError.add(piece.position);
                //System.out.println("pb!");
                return lPositionError;

        }
    } //depuis que Piece est une classe abstraite, je ne sais pas si ça marche.
    //échec
    public boolean check (List<Piece> piecesOnBoard, Board currentBoard){        //si ya échec -> dans la finalPosition"Piece" il y a une position négative
        boolean check=false;
        for (int i=0; i<piecesOnBoard.size(); i++){
            switch (piecesOnBoard.get(i).moveType){
                case PION:
                    check=helpcheck(finalPositionsPion( piecesOnBoard.get(i).getPosition(), currentBoard));
                case TOWER:
                    check=helpcheck(finalPositionsTower ( piecesOnBoard.get(i).getPosition(), currentBoard));
                case KNIGHT:
                    check=helpcheck(finalPositionsKnight ( piecesOnBoard.get(i).getPosition(), currentBoard));
                case FOUL:
                    check=helpcheck(finalPositionsFoul ( piecesOnBoard.get(i).getPosition(), currentBoard));
                case QUEEN:
                    check=helpcheck(finalPositionsQueen ( piecesOnBoard.get(i).getPosition(), currentBoard));
                case KING:
                    check=helpcheck(finalPositionsKing ( piecesOnBoard.get(i).getPosition(), currentBoard));
            }
            if(check==true){
                return true;
            }
        }
        return false;
    }
    public boolean helpcheck(List<Position> finalPositions){
        for (int i=0; i<finalPositions.size(); i++){
            if (finalPositions.get(i).column<0 && finalPositions.get(i).line<0){ return true; }
        }
        return false;
    }


    //finalPositionsPieces
    public List<Position> finalPositionsPion(Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        finalPosition.line = position.line;
        if (this.color == White) {
            if (this.alreadyMoved = Boolean.FALSE) {
                IsAbleToMovePionHelp2 (1, position, finalPosition, currentBoard, finalPositions);
                IsAbleToMovePionHelp2 (2, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            } else {
                IsAbleToMovePionHelp2 (1, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            }
        } else {
            if (this.alreadyMoved = Boolean.FALSE) {
                IsAbleToMovePionHelp2 (-1, position, finalPosition, currentBoard, finalPositions);
                IsAbleToMovePionHelp2 (-2, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            } else {
                IsAbleToMovePionHelp2 (-1, position, finalPosition, currentBoard, finalPositions);
                return (finalPositions);
            }
        }
    }
    public List<Position> finalPositionsTower (Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        IsAbleToMoveFoulTowerQueen (0, 1, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (0, -1, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (1, 0, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (-1, 0, position, finalPosition, currentBoard, finalPositions);
        return finalPositions;
    }
    public List<Position> finalPositionsKnight(Position position, Board currentBoard){
        Position finalPosition=new Position();
        List<Position> finalPositions = new ArrayList<>();
        int l;          //l pour ligne
        int c=-2;      //c pour colone
        while(c<=2){
            switch(c){
                case -2 :
                    IsAbleToMoveKnightHelp(1, c, finalPosition, currentBoard, finalPositions);  break;
                case -1 :
                    IsAbleToMoveKnightHelp(2, c, finalPosition, currentBoard, finalPositions);  break;
                case 0 :
                    c++;    break;
                case 1 :
                    IsAbleToMoveKnightHelp(2, c, finalPosition, currentBoard, finalPositions);  break;
                case 2 :
                    IsAbleToMoveKnightHelp(1, c, finalPosition, currentBoard, finalPositions);  break;
            }
            c++;
        }
        return finalPositions;
    }
    public List<Position> finalPositionsFoul(Position position, Board currentBoard){
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        IsAbleToMoveFoulTowerQueen (1, 1, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (-1, 1, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (1, -1, position, finalPosition, currentBoard, finalPositions);
        IsAbleToMoveFoulTowerQueen (-1, -1, position, finalPosition, currentBoard, finalPositions);
        return finalPositions;
    }
    public List<Position> finalPositionsQueen (Position position, Board currentBoard) {
        Position finalPosition = new Position(position.line, position.column);
        List<Position> finalPositions = new ArrayList<>();
        int i =-1; int j;
        while (i<=1){
            j=-1;
            while (j<=1){
                if (i==0 && j==0){
                    j++;
                }IsAbleToMoveFoulTowerQueen (i, j, position, finalPosition, currentBoard, finalPositions);
                j++;
            }i++;
        }
        return finalPositions;
    }
    public List<Position> finalPositionsKing(Position position, Board currentBoard){
        Position finalPosition=new Position();
        List<Position> finalPositions = new ArrayList<>();
        IsAbleToMoveKing (position, finalPosition, currentBoard, finalPositions);
        if (this.alreadyMoved=false){                                                                   //idée pour le rock
            if (currentBoard.boardMatrix[0][finalPosition.line].getPiece().alreadyMoved=false){         //rock
                finalPosition.line=position.line;                                                       //rock
                finalPosition.column=2;                                                                 //rock
            }                                                                                           //rock
        }                                                                                               //rock
        return finalPositions;
    }


    //finalPositionsPieces Help
    public List<Position> IsAbleToMoveFoulTowerQueen (int line, int column, Position position,
                                                      Position finalPosition, Board currentBoard, List<Position> finalPositions) {
        while(true){

            if (line>0)   {finalPosition.line=position.line+line;}
            else{finalPosition.line=position.line-line;}

            if (column>0)   {finalPosition.column=position.column+column;}
            else{finalPosition.column=position.column-column;}

            if ( finalPosition.line>=0 || finalPosition.line<=7 ){  break;  }                   //si on sort du plateau
            if( finalPosition.column>=0 || finalPosition.column<=7 ){  break;  }                //si on sort du plateau
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color==this.color) { break; }    //si on "rencontre" une piece de la même couleur
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color!=this.color &&             //si on "rencontre" le roi adverse, on renvoit une position négative
                    currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType==KING){
                Position echec = new Position(-1,-1);
                finalPositions.add(echec);
                break;}
            finalPositions.add(finalPosition);
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color!=this.color) {   break;  }     //si on "rencontre" une piece adverse

            if (line>0){line++;}                            //si on veut accrémenter la ligne, on met 1 dans l'argument line                                       //si on veut décrémenter la ligne, on met -1 dans l'argument line
            if (line<0){line--;}                             //si on veut décrémenter la ligne, on met -1 dans l'argument line                           //si on veut ne pas toucher à la ligne, put 0 dans l'argument line
            if (column>0){column++;}                          //de même pour column
            if (column<0){column--;}
        }
        return finalPositions;
    }
    public List<Position> IsAbleToMoveKing (Position position, Position finalPosition, Board currentBoard, List<Position> finalPositions){
        int column=-1;
        int line;
        while(column<=1){
            finalPosition.column=position.column+column;
            line=-1;
            while(line<=1){
                if (column==0 && line==0){
                    line++;
                }
                finalPosition.line=position.line+line;
                IsAbleToMoveKingHelp(finalPosition, currentBoard, finalPositions);
                line++;
            }
            column++;
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
    public List<Position> IsAbleToMoveKingHelp(Position finalPosition, Board currentBoard, List<Position> finalPositions){
        while(true){
            if (finalPosition.line>=0 || finalPosition.line<=7) { break; }
            if (finalPosition.column >= 0 || finalPosition.column <= 7) { break; }
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color==this.color) { break; }
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color!=this.color &&             //si on "rencontre" le roi adverse, on renvoit une position négative
                    currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType==KING){
                Position echec = new Position(-1,-1);
                finalPositions.add(echec);
                break;}
            finalPositions.add(finalPosition);
            if ( currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color!=this.color) {   break;  }
        }
        return finalPositions;
    }
    public List<Position> IsAbleToMovePionHelp (Position position, Position finalPosition, Board currentBoard, List < Position > finalPositions){
        if (currentBoard.boardMatrix[position.column - 1][position.line + 1].getPiece().color != this.color
                && currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType==KING){
            Position echec = new Position(-1,-1);
            finalPositions.add(echec);                                                                                       //si échec
        }
        if (currentBoard.boardMatrix[position.column + 1][position.line + 1].getPiece().color != this.color
                && currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType==KING){
            Position echec = new Position(-1,-1);
            finalPositions.add(echec);                                                                                      //si échec
        }

        if (currentBoard.boardMatrix[position.column - 1][position.line + 1].getPiece().color != this.color) {
            finalPositions.add(finalPosition);}
        if( currentBoard.boardMatrix[position.column + 1][position.line + 1].getPiece().color != this.color
                && currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType!=KING) {
            finalPositions.add(finalPosition);}
        if (finalPosition.line >= 0 || finalPosition.line <= 7) {
            if (finalPosition.column >= 0 || finalPosition.column <= 7) {
                if (!currentBoard.boardMatrix[finalPosition.column][finalPosition.line].isOccupied()) {
                    finalPositions.add(finalPosition);}
            }
        }
        return finalPositions;
    } //ne fonctionne peut être pas
    public List<Position> IsAbleToMovePionHelp2 (int i, Position position, Position finalPosition, Board currentBoard, List < Position > finalPositions) {
        finalPosition.column = position.column + i;
        IsAbleToMovePionHelp(position, finalPosition, currentBoard, finalPositions);
        return (finalPositions);
    }


    // getters & Setters
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public MoveType getMoveType() {return moveType;}
    public void setMoveType(MoveType moveType) {this.moveType = moveType;}
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public boolean isOnBoard() {
        return isOnBoard;
    }
    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }
    public Boolean getAlreadyMoved() {
        return alreadyMoved;
    }
    public void setAlreadyMoved(Boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }
    public int getNumberPion() {
        return numberPion;
    }
    public void setNumberPion(int numberPion) {
        this.numberPion = numberPion;
    }
}
