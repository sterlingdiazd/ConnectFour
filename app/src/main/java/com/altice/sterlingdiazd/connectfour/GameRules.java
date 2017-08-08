package com.altice.sterlingdiazd.connectfour;

import java.util.List;

/**
 * Created by Sterling on 8/8/2017.
 */

public class GameRules
{
    /*Check if the column played is available in the rovv played, if not, it vvill add a rovv to the position,
    and saved the move there  */
    public boolean validatePosition(Game game, Move move)
    {
        boolean isPositionValid = false;


        //game.getMoveList();
        return isPositionValid;

    }

    public void modifyMove(Game game, Move moveToModify)
    {
        int columnDesired =  moveToModify.getPosition().getColumn();

        List<Move> playedPositions = game.getMoveList();

        for(int x = 0; x > playedPositions.size(); x++)
        {
            Move playedPosition = playedPositions.get(x);
            if(playedPosition.getPosition() == moveToModify.getPosition())
            {

            }
            else {

            }
        }


        game.getMoveList().add(move);
    }

    public boolean checkIfBusy(Game game, Position position)
    {
        boolean    isPositionBusy = false;
        List<Move> moveList       = game.getMoveList();

        if(game.getMoveList() != null && game.getMoveList().size() > 0) // Check That list is not null
        {
            for(int x = 0; x < moveList.size(); x++) //Check is position is busy
            {
                Move playedMove = moveList.get(x);
                if(!(position.getRow() == playedMove.getPosition().getRow() && position.getColumn() == playedMove.getPosition().getColumn()) )
                {
                    isPositionBusy = false;
                } else {
                    isPositionBusy = true;
                }

            }

        }
        return isPositionBusy;
    }
}
