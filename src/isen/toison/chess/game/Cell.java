package isen.toison.chess.game;

import isen.toison.chess.pieces.Color;
import isen.toison.chess.pieces.Piece;
import isen.toison.chess.pieces.Position;

/**
 * Created by isen on 18/10/2016.
 */
public class Cell{
    public Piece piece;
    public Color color;
    public Position position;
    public boolean isOccupied;

    //constructor
    public Cell(Piece piece, Position position) {      //constructor
        this.piece = piece;
        this.position = position;}
    public Cell(Color color, Position position) {       //constructor
        this.color = color;
        this.position = position;}
    public Cell(Position position){                    //constructor
        this.position = position;}
    public Cell(Piece piece, Color color) {             //constructor
        this.piece = piece;
        this.color = color;}

    //getter & setter
    public Color getColor() {
        return color;
    }                               //color
    public void setColor(Color color) {
        this.color = color;
    }
    public Piece getPiece() {
        return piece;}                                           //Piece
    public void setPiece(Piece piece) {
        this.piece = piece;}
    public boolean isOccupied() {
        return isOccupied;
    }                      //isoccupied
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    public Position getPosition() {return position;}                        //position
    public void setPosition(Position position) {this.position = position;}

    //other methods
    public void clear() {
        piece = null;
        isOccupied = false;
    }

}
