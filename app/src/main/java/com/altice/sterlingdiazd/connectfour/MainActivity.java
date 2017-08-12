package com.altice.sterlingdiazd.connectfour;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, DialogInterface.OnClickListener, View.OnClickListener
{
    private GridView gridViewBoard;
    private SquareAdapter squareAdapter;
    boolean isFirstPlayer = true;
    private GameRules gameRules;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        activity = MainActivity.this;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridViewBoard = (GridView) findViewById(R.id.gridViewBoard);
        findViewById(R.id.buttonPlayAgain).setOnClickListener(this);
        findViewById(R.id.buttonCleanBoard).setOnClickListener(this);
        findViewById(R.id.buttonExit).setOnClickListener(this);

        gameRules = new GameRules(MainActivity.this);
        squareAdapter = new SquareAdapter(this, gameRules.createBoard());
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
            MediaPlayer.create(this, R.raw.clicked).start();
            Position clickedPosition = (Position) parent.getItemAtPosition(position);

            Move move = new Move();
            move.setViewPosition(position);
            move.setPosition(new Position(clickedPosition.getRow(), clickedPosition.getColumn()));
            move.setFirstPlayer(isFirstPlayer);

            String validationMessage = gameRules.playPosition(move);

            if(validationMessage.equalsIgnoreCase(GameRules.validMove))
            {
                View view1 = parent.getChildAt(move.getViewPosition());
                if(isFirstPlayer)
                {
                    squareAdapter.changeBackGround(view1, R.drawable.button_player_one);
                } else {
                    squareAdapter.changeBackGround(view1, R.drawable.button_player_two);
                }
                isFirstPlayer = !isFirstPlayer;
            }
            if(validationMessage.equalsIgnoreCase(GameRules.usedPosition))
            {
                Toast.makeText(this, getResources().getString(R.string.used_position), Toast.LENGTH_LONG).show();
            }

            WinningMove winningMove = gameRules.checkForWinningMove(move);
            if(winningMove.isWon())
            {
                Toast.makeText(this, winningMove.getWonMessage(), Toast.LENGTH_LONG).show();

                MediaPlayer.create(this, R.raw.win).start();
                gridViewBoard.setEnabled(false);
                playAgain(activity.getResources().getString(R.string.win_again_message));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override public void onClick(DialogInterface dialog, int which)
    {
        String message;
        switch (which)
        {
            case DialogInterface.BUTTON_POSITIVE:
                MediaPlayer.create(activity, R.raw.win).start();
                message =activity.getResources().getString(R.string.ready_message);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                Game.cleanBoard(MainActivity.this, gridViewBoard, squareAdapter);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                message =activity.getResources().getString(R.string.lose_again_message);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                Game.cleanBoard(MainActivity.this, gridViewBoard, squareAdapter);
                finish();
                break;
        }

    }

    public void playAgain(String playMessage)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(playMessage)
                .setMessage(activity.getResources().getString(R.string.try_again_message))
                .setPositiveButton(activity.getResources().getString(R.string.yes), this)
                .setNegativeButton(activity.getResources().getString(R.string.no), this)
                .create();
        alertDialog.show();
    }


    @Override public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonPlayAgain:
                String playMessage = activity.getResources().getString(R.string.play_again);
                playAgain(playMessage);
                break;
            case R.id.buttonCleanBoard:
                Game.cleanBoard(MainActivity.this, gridViewBoard, squareAdapter);
                break;
            case R.id.buttonExit:
                Toast.makeText(this, activity.getResources().getString(R.string.lose_again_message), Toast.LENGTH_LONG).show();
                Game.cleanBoard(MainActivity.this, gridViewBoard, squareAdapter);
                finish();
                break;

        }
    }
}
