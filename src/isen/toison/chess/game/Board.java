package isen.toison.chess.game;

import isen.toison.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.Color.*;
import static isen.toison.chess.pieces.MoveType.*;

/**
 * Created by isen on 18/10/2016.
 */
public class Board {
    public Cell[][] boardMatrix = new Cell[8][8];


    //constructor
    public Board(Cell[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }


    //initialisation
    public  void initBoard() {
        Position position = new Position();
        int i, j;                   //j : longueur i :largeur
        for (j=0; j<= 7; j++) {
            if ((j + 2) % 2 == 0) {
                for (i=0; i<7; i=i+2) {
                    position.line=j;
                    position.column=i;
                    boardMatrix[i][j] = new Cell(White, position);
                    boardMatrix[i][j].setOccupied(false);
                    i++;
                    position.column=i;
                    boardMatrix[i][j] = new Cell(Black, position);
                    boardMatrix[i][j].setOccupied(false);
                }
            } else {
                for (i=0; i<7; i=i+2) {
                    position.line=j;
                    position.column=i;
                    boardMatrix[i][j] = new Cell(Black, position);
                    boardMatrix[i][j].setOccupied(false);
                    i++;
                    position.column=i;
                    boardMatrix[i][j] = new Cell(White, position);
                    boardMatrix[i][j].setOccupied(false);
                }
            }
        }
    }
    private Cell[][] initPieceOnBoard(){
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                if (i == 0) {
                    switch (j){
                        case 0 :
                            boardMatrix[i][j].setPiece(new Tower(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 1 :
                            boardMatrix[i][j].setPiece(new Knight(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 2 :
                            boardMatrix[i][j].setPiece(new Foul(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 3 :
                            boardMatrix[i][j].setPiece(new Queen(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 4 :
                            boardMatrix[i][j].setPiece(new King(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 5 :
                            boardMatrix[i][j].setPiece(new Foul(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 6 :
                            boardMatrix[i][j].setPiece(new Knight(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 7 :
                            boardMatrix[i][j].setPiece(new Tower(White, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                    }
                }
                if (i == 1) {
                    boardMatrix[i][j].setPiece(new Pion(White, new Position(i, j), j));
                    boardMatrix[i][j].setOccupied(true);
                }
                if (i == 6) {
                    boardMatrix[i][j].setPiece(new Pion(Black, new Position(i, j), j));
                    boardMatrix[i][j].setOccupied(true);
                }
                if (i == 7) {
                    switch (j){
                        case 0 :
                            boardMatrix[i][j].setPiece(new Tower(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 1 :
                            boardMatrix[i][j].setPiece(new Knight(Black,  new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 2 :
                            boardMatrix[i][j].setPiece(new Foul(Black,  new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 3 :
                            boardMatrix[i][j].setPiece(new Queen(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 4 :
                            boardMatrix[i][j].setPiece(new King(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 5 :
                            boardMatrix[i][j].setPiece(new Foul(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 6 :
                            boardMatrix[i][j].setPiece(new Knight(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                        case 7 :
                            boardMatrix[i][j].setPiece(new Tower(Black, new Position(i,j)));
                            boardMatrix[i][j].setOccupied(true);
                            break;
                    }
                }
            }
        }
        return(boardMatrix);
    }           //place les pièces sur l'échiquier

    //action pièce

        //déplacement pièce
        public void move (Position oldPosition, Position newPosition, Board currentBoard){
        if (currentBoard.boardMatrix[newPosition.column][newPosition.line].isOccupied())    {
            currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().isOnBoard=false;

        }
        currentBoard.boardMatrix[newPosition.column][newPosition.line]
                .setPiece(currentBoard.boardMatrix[oldPosition.column][oldPosition.line].getPiece());
        currentBoard.boardMatrix[oldPosition.column][oldPosition.line].clear();
        alreadymovedPionKingTower(newPosition, currentBoard);
    }
        public void alreadymovedPionKingTower(Position newPosition, Board currentBoard){
            if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType==PION )  {
                currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
            }
            if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType==KING)   {
                currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
            }
            if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType==TOWER)  {
                currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
            }
        }

}

