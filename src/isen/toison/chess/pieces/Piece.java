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
	public Boolean alreadyMoved;
	public int numberPion;
	List<Position> finalPositions;

	public Piece(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	// constructors


	//depuis que Piece est une classe abstraite, je ne sais pas si ça marche.
	//échec
	public boolean check(List<Piece> piecesOnBoard, Board currentBoard) {        //si ya échec -> dans la finalPosition"Piece" il y a une position négative
		boolean check = false;
		for (int i = 0; i < piecesOnBoard.size(); i++) {
			check = helpcheck(finalPositions);
			if (check) {
				return true;
			}
		}
		return false;
	}

	public boolean helpcheck(List<Position> finalPositions) {
		for (int i = 0; i < finalPositions.size(); i++) {
			if (finalPositions.get(i).column < 0 && finalPositions.get(i).line < 0) {
				return true;
			}
		}
		return false;
	}


	//déplacement pièce
	public void move(Position oldPosition, Position newPosition, Board currentBoard) {
		if (currentBoard.boardMatrix[newPosition.column][newPosition.line].isOccupied()) {
			currentBoard.boardMatrix[newPosition.column][newPosition.line].getPiece().isOnBoard = false;

		}
		currentBoard.boardMatrix[newPosition.column][newPosition.line]
				.setPiece(currentBoard.boardMatrix[oldPosition.column][oldPosition.line].getPiece());
		currentBoard.boardMatrix[oldPosition.column][oldPosition.line].setPiece(null);
	}

	//finalPositionsPieces
	public abstract List<Position> finalPositions(Position position, Board currentBoard);

	//finalPositionsPieces Help
	public abstract List<Position> IsAbleToMove(Position finalPosition, Board currentBoard);


	public List<Position> IsAbleToMoveKnightHelp(int i, int c, Position finalPosition, Board currentBoard) {
		finalPosition.line = position.line - i;
		finalPosition.column = position.column + c;
		IsAbleToMoveKingHelp(position, finalPosition, currentBoard);
		finalPosition.line = position.line + i;
		IsAbleToMoveKingHelp(position, finalPosition, currentBoard);
		return finalPositions;
	}

	public List<Position> IsAbleToMoveKingHelp(Position position, Position finalPosition, Board currentBoard) {
		while (true) {
			if (finalPosition.line >= 0 || finalPosition.line <= 7) {
				break;
			}
			if (finalPosition.column >= 0 || finalPosition.column <= 7) {
				break;
			}
			if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color == this.color) {
				break;
			}
			if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color != this.color &&             //si on "rencontre" le roi adverse, on renvoit une position négative
					currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType == KING) {
				Position echec = new Position(-1, -1);
				finalPositions.add(echec);
				break;
			}
			finalPositions.add(finalPosition);
			if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color != this.color) {
				break;
			}
		}
		return finalPositions;
	}

	public List<Position> IsAbleToMovePionHelp(Position position, Position finalPosition, Board currentBoard) {
		if (currentBoard.boardMatrix[position.column - 1][position.line + 1].getPiece().color != this.color
				&& currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType == KING) {
			Position echec = new Position(-1, -1);
			finalPositions.add(echec);                                                                                       //si échec
		}
		if (currentBoard.boardMatrix[position.column + 1][position.line + 1].getPiece().color != this.color
				&& currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType == KING) {
			Position echec = new Position(-1, -1);
			finalPositions.add(echec);                                                                                      //si échec
		}

		if (currentBoard.boardMatrix[position.column - 1][position.line + 1].getPiece().color != this.color) {
			finalPositions.add(finalPosition);
		}
		if (currentBoard.boardMatrix[position.column + 1][position.line + 1].getPiece().color != this.color
				&& currentBoard.boardMatrix[finalPosition.column - 1][finalPosition.line + 1].getPiece().moveType != KING) {
			finalPositions.add(finalPosition);
		}
		if (finalPosition.line >= 0 || finalPosition.line <= 7) {
			if (finalPosition.column >= 0 || finalPosition.column <= 7) {
				if (!currentBoard.boardMatrix[finalPosition.column][finalPosition.line].isOccupied()) {
					finalPositions.add(finalPosition);
				}
			}
		}
		return finalPositions;
	} //ne fonctionne peut être pas

	public List<Position> IsAbleToMovePionHelp2(int i, Position position, Position finalPosition, Board currentBoard) {
		finalPosition.column = position.column + i;
		IsAbleToMovePionHelp(position, finalPosition, currentBoard);
		return (finalPositions);
	}


	// getters & Setters
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

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
