package com.example.minesweeper;

import kotlin.math.UMathKt;

public  class record {
    public int wins,games;
    public  float besttime,avgtime;
    public  record(){
        wins =0;
        games=0;
        avgtime =0;
        besttime =0;
    }
    public  void uptade(float newtime){
        games++;
        if(games==1) {
            avgtime =newtime;
            besttime=newtime;
        }
        else{
            avgtime =(avgtime+newtime)/2;
            if(newtime<besttime)besttime=newtime;
        }
    }
}