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
	List<Position> finalPositions = new ArrayList<>();

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


	//finalPositionsPieces
	public abstract List<Position> finalPositions(Position finalPosition, Board currentBoard);

	//finalPositionsPieces Help
	public abstract List<Position> isAbleToMove(Position finalPosition, Board currentBoard);


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
