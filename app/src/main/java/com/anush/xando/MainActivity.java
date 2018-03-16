package com.anush.xando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 is x and 1 is O
    int activepalyer=0;

    boolean gameActive=true;
    boolean drawtry=true;
    boolean gameDraw=true;
    // 2 is unplayed
    int[] gamestate={2,2,2,2,2,2,2,2,2};

    int[][]winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter=(ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());


        if(gamestate[tappedCounter]==2 && gameActive) {

            gamestate[tappedCounter]=activepalyer;

            counter.setTranslationY(1000f);

            if (activepalyer == 0) {
                counter.setImageResource(R.drawable.x);
                activepalyer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activepalyer = 0;
            }
            counter.animate().translationYBy(-1000f).setDuration(1000);

            for (int[] winningposition:winningpositions){
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]]&&gamestate[winningposition[1]]==gamestate[winningposition[2]]
                        &&gamestate[winningposition[0]]!=2){
                    String winnername="First Player ";

                    if(gamestate[winningposition[0]]==1){
                        winnername="Second Player ";
                    }
                    drawtry=false;
                    gameActive=false;
                    TextView winner=(TextView)findViewById(R.id.winnertext);
                    winner.setText(winnername+"has won!");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.layout1);

                    layout.setTranslationY(-1000f);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate().translationYBy(1000f).setDuration(1000);
                }

            }
if(drawtry){gameDraw=true;
            for(int drawstates:gamestate){

                if(drawstates==2){gameDraw=false;}}
                if(gameDraw){
                TextView winner=(TextView)findViewById(R.id.winnertext);
                    winner.setText("It is a draw");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.layout1);
                    layout.setTranslationY(-1000f);
                    layout.setVisibility(View.VISIBLE);
                   layout.animate().translationYBy(1000f).setDuration(1000);}
            }

        }

    }

    public void playAgain(View view){
        activepalyer=0;
        gameActive=true;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;

        }

        LinearLayout layout=(LinearLayout)findViewById(R.id.layout1);
        layout.setVisibility(View.INVISIBLE);

        GridLayout grid=(GridLayout)findViewById(R.id.grid);

        for(int i=0;i<grid.getChildCount();i++){
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }

    }
    public void exit(View view){
        Intent intent=new Intent(getApplicationContext(),start.class);
        startActivity(intent);
        finish();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
