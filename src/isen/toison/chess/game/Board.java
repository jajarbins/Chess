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
	public Board() {
	}

	@Override
	public String toString() {
		String boardGraph = "";
		for (int i = 0; i <= 7; i++){
			for (int j = 0; j <= 7; j++) {
				boardGraph += "|";
				if (boardMatrix[i][j].isOccupied){
					boardGraph += "[";
					boardGraph += boardMatrix[i][j].getPiece().getMoveType().toString();
					boardGraph += boardMatrix[i][j].getPiece().getColor() == White ? "w" : "b";
					boardGraph += "]";
				}
				else boardGraph += "    ";
				boardGraph += "|";
			}
			boardGraph += "  ";
			for (int k = 0; k <= 7; k++) {
				int line = boardMatrix[i][k].getPosition().line;
				int column = boardMatrix[i][k].getPosition().column;
				boardGraph += "|";
				if (boardMatrix[i][k].isOccupied()) boardGraph += "[" + line + column + "]";
				else boardGraph += " " + line + column + " ";
				boardGraph += "|";
			}
			boardGraph += "\n";
		}
		return boardGraph;
	}


	//initialisation
	public void initBoard() {
		int i, j;                   //j : longueur i :largeur
		Color colorSwap = Color.Black;
		for (j = 0; j <= 7; j++) {
			for (i = 0; i <= 7; i++) {
				Position position = new Position();
				colorSwap = colorSwap == Color.Black ? Color.White : Color.Black;
				position.line = j;
				position.column = i;
				boardMatrix[j][i] = new Cell(colorSwap, position);
				boardMatrix[j][i].setOccupied(false);
			}
		}
	}

	public Cell[][] initPieceOnBoard() {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (i == 0) {
					switch (j) {
						case 0:
							boardMatrix[i][j].setPiece(new Tower(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 1:
							boardMatrix[i][j].setPiece(new Knight(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 2:
							boardMatrix[i][j].setPiece(new Foul(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 3:
							boardMatrix[i][j].setPiece(new Queen(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 4:
							boardMatrix[i][j].setPiece(new King(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 5:
							boardMatrix[i][j].setPiece(new Foul(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 6:
							boardMatrix[i][j].setPiece(new Knight(White, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 7:
							boardMatrix[i][j].setPiece(new Tower(White, new Position(i, j)));
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
					switch (j) {
						case 0:
							boardMatrix[i][j].setPiece(new Tower(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 1:
							boardMatrix[i][j].setPiece(new Knight(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 2:
							boardMatrix[i][j].setPiece(new Foul(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 3:
							boardMatrix[i][j].setPiece(new Queen(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 4:
							boardMatrix[i][j].setPiece(new King(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 5:
							boardMatrix[i][j].setPiece(new Foul(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 6:
							boardMatrix[i][j].setPiece(new Knight(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
						case 7:
							boardMatrix[i][j].setPiece(new Tower(Black, new Position(i, j)));
							boardMatrix[i][j].setOccupied(true);
							break;
					}
				}
			}
		}
		return (boardMatrix);
	}           //place les pièces sur l'échiquier

	//action pièce


	//déplacement pièce
	public boolean movePiece(Position oldPosition, final Position newPosition) {
		Cell cellOldPos = boardMatrix[oldPosition.line][oldPosition.column];
		Cell cellNewPos = boardMatrix[newPosition.line][newPosition.column];
		Piece pieceToMove = cellOldPos.getPiece();
		if (pieceToMove.isAbleToMove(newPosition, this)
				.stream()
				.anyMatch(finalPos -> finalPos.line == newPosition.line
				&& finalPos.column == newPosition.column)){
			//swap
			if (cellNewPos.isOccupied) {
				cellNewPos.getPiece().isOnBoard = false;
			}
			cellNewPos.setPiece(this.boardMatrix[oldPosition.column][oldPosition.line].getPiece());
			cellOldPos.setPiece(null);

			return true;
		}

		return false;
	}

	public void alreadymovedPionKingTower(Position newPosition, Board currentBoard) {
		if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType == PION) {
			currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
		}
		if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType == KING) {
			currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
		}
		if (currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().moveType == TOWER) {
			currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().setAlreadyMoved(true);
		}
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
	public List<Piece> pieceOnBoardByColor (Color pieceColor){
		List<Piece> pieceOnBoardW = pieceOnBoard(this);
		for (int i=0; i<pieceOnBoardW.size(); i++){
			if (pieceOnBoardW.get(i).getColor() == pieceColor)
				pieceOnBoardW.remove(i);
		}
		return pieceOnBoardW;
	}
}

