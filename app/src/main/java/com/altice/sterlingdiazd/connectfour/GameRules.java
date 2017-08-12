package com.altice.sterlingdiazd.connectfour;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sterling on 8/8/2017.
 */

public class GameRules
{
    private Context context;
    static final String validMove = "Valid";
    static final String usedPosition = "BusyPosition";

    private static final String izquierda = "Izquierda";
    private static final String derecha   = "Derecha";


    GameRules(Context context)
    {
        this.context = context;
    }

    String playPosition(Move move)
    {
        boolean isPositionValid = false;
        String  message         = null;
        int playedInRow = move.getPosition().getRow();
        int rowsToEvaluate = Game.rowSize - playedInRow;
        Position positionToEvaluate = move.getPosition();

        for (int x = 0; x <= rowsToEvaluate; x++)
        {
            if (checkIfBusy(positionToEvaluate))
            {
                message = usedPosition;
                break;
            }
            else
            {
                //Evaluar si la posicion de abajo esta libre, si lo está, moverla a la de abajo

                if (playedInRow < Game.rowSize)
                {
                    playedInRow++;
                    move.setViewPosition((move.getViewPosition() + Game.columnSize));
                    positionToEvaluate.setRow(playedInRow);
                    if (checkIfBusy(positionToEvaluate)) //La Posicion de abajo no esta libre
                    {
                        playedInRow--;
                        move.setViewPosition((move.getViewPosition() - Game.columnSize));
                        positionToEvaluate.setRow(playedInRow);
                        modifyMove(move);
                        return validMove;
                    }
                }
                else
                {
                    modifyMove(move);
                    return validMove;
                }
            }
        }
        return message;
    }

    private void modifyMove(Move moveToModify)
    {
        Game.getInstance().getMoveList().add(moveToModify);
    }

    private boolean checkIfWonVertically(Move moveToCheckIfWonVertically)
    {
        int positionToWinVertically = 4;
        boolean wonVertically = false;
        List<Move> moveInSameRow = new ArrayList<>();

        for (Move move : Game.getInstance().getMoveList() )
        {
            if (
                    move.getPosition().getColumn() == moveToCheckIfWonVertically.getPosition().getColumn()
                    && move.isFirstPlayer() == moveToCheckIfWonVertically.isFirstPlayer()
                    )
            {
                moveInSameRow.add(move);
            }
        }

        List<Integer> rowPositions = new ArrayList<>();

        for(Move move : moveInSameRow)
        {
            rowPositions.add(Integer.parseInt(String.valueOf(move.getPosition().getRow())));
        }
        Collections.sort(rowPositions);

        int previousRowNumber = 0;
        List<Boolean> numberAreContiguous = new ArrayList<>();
        boolean areContiguous = false;

        for(int x = 0; x < rowPositions.size(); x++)
        {
            if(x != 0)
            {
                Integer currentRowNumber = rowPositions.get(x);
                if( (previousRowNumber+1) == currentRowNumber)
                {
                    areContiguous = true;
                    numberAreContiguous.add(true);

                    previousRowNumber = currentRowNumber;

                    if(numberAreContiguous.size() == 4)
                        break;

                } else {
                    break;
                }
            } else {
                previousRowNumber = rowPositions.get(0);
                numberAreContiguous.add(areContiguous);
            }
        }

        if(numberAreContiguous.size() == positionToWinVertically && areContiguous)
        {
            wonVertically = true;
        }
        return wonVertically;
    }

    private boolean checkIfBusy(Position position)
    {
        boolean    isPositionBusy = false;
        List<Move> moveList       = Game.getInstance().getMoveList();

        // Check That list is not null
        if (Game.getInstance().getMoveList() != null && Game.getInstance().getMoveList().size() > 0)
        {
            //Check is position is busy
            for (int x = 0; x < moveList.size(); x++)
            {
                Move playedMove = moveList.get(x);
                if (!(position.getRow() == playedMove.getPosition().getRow() &&
                      position.getColumn() == playedMove.getPosition().getColumn()))
                {
                    isPositionBusy = false;
                }
                else
                {
                    isPositionBusy = true;
                    return isPositionBusy;
                }

            }

        }
        return isPositionBusy;
    }

    private boolean checkIfWonHorizontally(Move moveToCheckIfWonHorizontally)
    {
        int positionToWinHorizontally = 4;
        boolean wonHorizontally = false;
        List<Move> moveInSameRow = new ArrayList<>();

        for (Move move : Game.getInstance().getMoveList() )
        {
            if (
               move.getPosition().getRow() == moveToCheckIfWonHorizontally.getPosition().getRow()
               && move.isFirstPlayer() == moveToCheckIfWonHorizontally.isFirstPlayer()
               )
            {
                moveInSameRow.add(move);
            }
        }

        List<Integer> columnPositions = new ArrayList<>();

        for(Move move : moveInSameRow)
        {
            columnPositions.add(Integer.parseInt(String.valueOf(move.getPosition().getColumn())));
        }
        Collections.sort(columnPositions);

        int previousColumnNumber = 0;
        List<Boolean> numberAreContiguous = new ArrayList<>();
        boolean areContiguous = false;

        for(int x = 0; x < columnPositions.size(); x++)
        {
            if(x != 0)
            {
                Integer currentColumnNumber = columnPositions.get(x);
                if( (previousColumnNumber+1) == currentColumnNumber)
                {
                    areContiguous = true;
                    numberAreContiguous.add(true);
                    previousColumnNumber = currentColumnNumber;

                    if(numberAreContiguous.size() == 4)
                        break;

                } else {
                    break;
                }
            } else {
                previousColumnNumber = columnPositions.get(0);
                numberAreContiguous.add(areContiguous);
            }
        }

        if(numberAreContiguous.size() == positionToWinHorizontally && areContiguous)
        {
            wonHorizontally = true;
        }
        return wonHorizontally;
    }

    private boolean checkIfWonDiagonally(Move moveToCheckIfWonDiagonally)
    {
        int positionToWin = 4;
        boolean won = false;
        int diagonalConnection = 1;
        String diagonalConnectionOrientation = null;

        for(int x = 0; x < positionToWin; x++)
        {
            List<Move> moveList = Game.getInstance().getMoveList();
            for (Move move :  moveList)
            {
                if(diagonalConnectionOrientation == null || diagonalConnectionOrientation.equalsIgnoreCase( izquierda) )
                {
                    if(move.isFirstPlayer() == moveToCheckIfWonDiagonally.isFirstPlayer()
                       &&

                       ( (moveToCheckIfWonDiagonally.getPosition().getRow() + diagonalConnection) == move.getPosition().getRow()
                         &&
                         (moveToCheckIfWonDiagonally.getPosition().getColumn() - diagonalConnection) == move.getPosition().getColumn()
                       )
                            )
                    {
                        diagonalConnection += 1;
                        diagonalConnectionOrientation = izquierda;
                        break;
                    }
                }
                if(diagonalConnectionOrientation == null || diagonalConnectionOrientation.equalsIgnoreCase(derecha)  )
                {
                    if(move.isFirstPlayer() == moveToCheckIfWonDiagonally.isFirstPlayer()
                       &&

                       (moveToCheckIfWonDiagonally.getPosition().getRow() + diagonalConnection) == move.getPosition().getRow()
                       &&
                       (moveToCheckIfWonDiagonally.getPosition().getColumn() + diagonalConnection) == move.getPosition().getColumn()

                            )
                    {
                        diagonalConnection += 1;
                        diagonalConnectionOrientation = "Derecha";
                        break;
                    }
                }
            }
            if(diagonalConnection == positionToWin)
            {
                won = true;
                break;
            }
        }

        return won;
    }

    WinningMove checkForWinningMove(Move move)
    {
        boolean isWon = false;
        WinningMove winningMove = new WinningMove();

        if( checkIfWonVertically(move))
        {
            isWon = true;
            winningMove.setWonMessage(move, WinningMove.vertically);
        }

        if(checkIfWonHorizontally(move))
        {
            isWon = true;
            winningMove.setWonMessage(move, WinningMove.horizontally);
        }

        if(checkIfWonDiagonally(move))
        {
            isWon = true;
            winningMove.setWonMessage(move, WinningMove.diagonally);
        }
        winningMove.setWon(isWon);
        return winningMove;
    }

    ArrayList<Position> createBoard()
    {
        ArrayList<Position> board = new ArrayList<>();
        for(int rows = 1; rows <= Game.rowSize; rows++)
        {
            for(int columns = 1; columns <= Game.columnSize; columns++)
            {
                board.add(new Position(rows, columns));
            }

        }
        return board;
    }
}
