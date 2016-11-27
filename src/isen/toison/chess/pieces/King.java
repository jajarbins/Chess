package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;

import java.util.ArrayList;
import java.util.List;

import static isen.toison.chess.pieces.MoveType.*;

/**
 * Created by isen on 18/10/2016.
 */
public class King extends Piece {
	public Boolean alreadyMoved;
	public Boolean rock;

	//constructor
	public King(Color color, Position position) {
		super(color, position);
		this.moveType = KING;
		this.alreadyMoved = Boolean.FALSE;
	}

	//finalpositions
	public List<Position> finalPositions(Position position, Board currentBoard) {
		Position finalPosition = new Position();
		List<Position> finalPositions = new ArrayList<>();
		isAbleToMove(finalPosition, currentBoard);
		if (this.alreadyMoved = false) {                                                                   //idée pour le rock
			if (currentBoard.boardMatrix[0][finalPosition.line].getPiece().alreadyMoved = false) {         //rock
				finalPosition.line = position.line;                                                       //rock
				finalPosition.column = 2;                                                                 //rock
			}                                                                                           //rock
		}                                                                                               //rock
		return finalPositions;
	}

	//movehelp
	public List<Position> isAbleToMove(Position finalPosition, Board currentBoard) {
		int column = -1;
		int line;
		while (column <= 1) {
			finalPosition.column = position.column + column;
			line = -1;
			while (line <= 1) {
				if (column == 0 && line == 0) {
					line++;
				}
				finalPosition.line = position.line + line;
				IsAbleToMoveKingHelp(finalPosition, currentBoard, finalPositions);
				line++;
			}
			column++;
		}
		return finalPositions;
	}

	private List<Position> IsAbleToMoveKingHelp(Position finalPosition, Board currentBoard, List<Position> finalPositions) {
		if (finalPosition.line >= 0 || finalPosition.line <= 8) {
			if (finalPosition.column >= 0 || finalPosition.column <= 8) {
				if (currentBoard.boardMatrix[finalPosition.column][finalPosition.line].isOccupied() == false
						|| currentBoard.boardMatrix[finalPosition.column][finalPosition.line].getPiece().color != this.color) {
					finalPositions.add(finalPosition);
				}
			}
		}
		return finalPositions;
	}

}



