package isen.toison.chess.pieces;

/**
 * Created by isen on 18/10/2016.
 */
public class Position {
    public int line;
    public int column;

    public Position(){
    }

    public Position (int line, int column){
        this.column=column;
        this.line=line;}

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
