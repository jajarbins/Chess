package isen.toison.chess.pieces;

import isen.toison.chess.game.Board;
import isen.toison.chess.game.Cell;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static isen.toison.chess.pieces.Color.Black;
import static isen.toison.chess.pieces.Color.White;
import static isen.toison.chess.pieces.MoveType.PION;

/**
 * Created by isen on 18/10/2016.
 */
public class Pion extends Piece {
	public Boolean AlreadyMoved = false;
	public int numPion;
	private int columnIndexInc;

	//constructor
	public Pion(Color color, Position position, int numPion) {
		super(color, position);
		this.moveType = PION;
		this.numPion = numPion;
	}


	//finalpositions
	public List<Position> finalPositions(Position finalPosition, Board currentBoard) {
		List<Position> finalPositions = new ArrayList<>();
		finalPosition.line = position.line;
		if (finalPosition.column >= 0 || finalPosition.column <= 7) {
			if (finalPosition.line >= 0 || finalPosition.line <= 7) {
				if (!this.AlreadyMoved) {
					columnIndexInc = this.color == White ? 2 : -2;
					finalPositions.add(new Position(position.line + columnIndexInc, position.column));
				}
				columnIndexInc = this.color == White ? 1 : -1;
				finalPositions.add(new Position(position.line + columnIndexInc, position.column));

				Position finalFlex = position;
				finalFlex.line -= 1;
				finalFlex.column -= 1;
				if (finalFlex.line >= 0 && finalFlex.column >= 0) {
					finalPositions.add(new Position(finalFlex.line, finalFlex.column));
				}
				finalFlex = position;
				finalFlex.line += 1;
				finalFlex.column += 1;
				if (finalFlex.line <= 7 && finalFlex.column <= 7) {
					finalPositions.add(new Position(finalFlex.line, finalFlex.column));
				}
			}
		}
		return finalPositions;
	}

	//moveHelp
	public List<Position> isAbleToMove(Position finalPosition, Board currentBoard) {
		return (finalPositions
				.stream()
				.filter(position1 ->
						currentBoard.boardMatrix[position1.line][position1.column].isOccupied()
								&&
								currentBoard.boardMatrix[position1.line][position1.column].getColor() != this.color)
				.collect(Collectors.toList()));
	} //ne fonctionne peut être pas

}

