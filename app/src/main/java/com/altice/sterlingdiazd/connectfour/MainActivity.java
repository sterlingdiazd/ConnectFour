package com.altice.sterlingdiazd.connectfour;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener
{
    private GridView gridViewBoard;
    private SquareAdapter squareAdapter;

    boolean isFirstPlayer = true;
    private Game game;
    private GameRules gameRules;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridViewBoard = (GridView) findViewById(R.id.gridViewBoard);

        game = new Game();
        gameRules = new GameRules();

        ArrayList<Position> board = new ArrayList<Position>();

        for(int columns = 1; columns <= Game.columnSize; columns++)
        {
            for(int rows = 1; rows <= Game.rowSize; rows++)
            board.add(new Position(columns, rows));
        }

        squareAdapter = new SquareAdapter(this, board);
        gridViewBoard.setAdapter(squareAdapter);
        gridViewBoard.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        try
        {
            Position clickedPosition = (Position) parent.getItemAtPosition(position);

            Toast.makeText(view.getContext(), "Position: " + clickedPosition.getRow() + "x" + clickedPosition.getColumn(),Toast.LENGTH_LONG).show();

            Move move = new Move();
            move.setPosition(clickedPosition);
            move.setFirstPlayer(isFirstPlayer);



            if(!gameRules.checkIfBusy(game, move.getPosition()))
            {
                boolean validMove = gameRules.validatePosition(game, move);
                if(validMove == false)
                {
                    gameRules.modifyMove(game, move);

                }
                if(isFirstPlayer)
                {
                    squareAdapter.changeBackGround(view, R.drawable.button_player_one);
                } else {
                    squareAdapter.changeBackGround(view, R.drawable.button_player_two);
                }
                game.getMoveList().add(move);
            } else {
                Toast.makeText(this, "Your Opponent already played here! Try another position", Toast.LENGTH_SHORT).show();
            }




            isFirstPlayer = !isFirstPlayer;
           // Square square = new Square("0", "1","1");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
