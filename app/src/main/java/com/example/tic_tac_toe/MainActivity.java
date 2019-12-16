package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  int current_palyer=0;
    public int[] gamestate={2,2,2,2,2,2,2,2,2,2};
    //2=unplayed state
public int[][] winpos={
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {1,5,9},
        {3,5,7},
        {1,4,7},
        {2,5,8},
        {3,6,9}
};

int check()
{
    for(int i=1;i<=9;i++)
    {
        if(gamestate[i]==1)
        {
            for(int j=0;j<8;j++)
            {
                if(winpos[j][0]==i)
                {
                    int c=0;
                    for(int k=1;k<3;k++)
                    {
if(gamestate[winpos[j][k]]==1) c++;
                    }
                    if(c==2) return 1;

                }


            }
        }
        else
        {
            if(gamestate[i]==0)
            {
                for(int j=0;j<8;j++)
                {
                    if(winpos[j][0]==i)
                    {
                        int c=0;
                        for(int k=1;k<3;k++)
                        {
                            if(gamestate[winpos[j][k]]==0) c++;
                        }
                        if(c==2) return 0;

                    }


                }
            }

        }

    }
    return 2;
}
    public void glow(View view)
    {
        ImageView zero_image=(ImageView)view;
        int state=Integer.parseInt(zero_image.getTag().toString());
        //Log.i(zero_image.getTag().toString(),"hi"+check());

        if(current_palyer==1)
        {
            if(gamestate[state]==2)
            {  zero_image.setTranslationY(-1200f);
                zero_image.setImageResource(R.drawable.one);
                gamestate[state]=1;
                current_palyer=0;
                zero_image.animate().translationYBy(1200f).setDuration(200);

            }


        }
        else
        {
            if(current_palyer==0)
            {
                if(gamestate[state]==2)
                {  zero_image.setTranslationY(-1200f);
                    zero_image.setImageResource(R.drawable.zero);
                    gamestate[state]=0;
                    current_palyer=1;
                    zero_image.animate().translationYBy(1200f).setDuration(200);


                }
            }

        }

        if(check()==1)
        {
            TextView wintext=(TextView)findViewById(R.id.win_text);
            wintext.setText("One win");
            current_palyer=-3;
        }
       else if(check()==0)
        {
            TextView wintext=(TextView)findViewById(R.id.win_text);
            wintext.setText("Zero win");
            current_palyer=-3;
        }

       else
        {
            int c=0;
            for(int i=0;i<10;i++)
            {
                if(gamestate[i]!=2) c++;
            }

            if(c==9)
            {
                TextView wintext=(TextView)findViewById(R.id.win_text);
                wintext.setText("Game Draw");
            }
        }
    }

    public void play_again(View view)
    {
        current_palyer=0;
        for(int i=1;i<10;i++)
        {
            gamestate[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid_of);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
