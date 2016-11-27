package isen.toison.chess;

import isen.toison.chess.game.Board;
import isen.toison.chess.game.Player;
import isen.toison.chess.pieces.Color;
import isen.toison.chess.pieces.Position;

import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {

        Board mainBoard = new Board();

        mainBoard.initBoard();
        mainBoard.initPieceOnBoard();
        System.out.println(mainBoard.toString());

        Player player1 = new Player(Color.White, mainBoard);
        Player player2 = new Player(Color.Black, mainBoard);

        System.out.println("white to play\n\n enter position XX of a piece : ");
        Scanner scanner = new Scanner(System.in);
        String piecePosString = scanner.nextLine();
        Position piecePos = new Position(Integer.valueOf(piecePosString.substring(0, 1)),
                Integer.valueOf(piecePosString.substring(1,2)));
        System.out.println("enter position XX to move to : ");
        String finalPiecePosString = scanner.nextLine();
        Position finalPiecePos = new Position(Integer.valueOf(finalPiecePosString.substring(0, 1)),
                Integer.valueOf(finalPiecePosString.substring(1,2)));
        if (mainBoard.movePiece(piecePos, finalPiecePos)){
            System.out.println(mainBoard.toString());
        }




    }
}
