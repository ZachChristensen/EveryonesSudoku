package zach.christensen.everyonessudoku.Model;

/**
 * Created by Zach on 25/06/2016.
 */

public class AbstractController {
    int getMax(){ return 0;};
    int getRowmax(){ return 0;};
    int getColumMax(){ return 0;};
    int getSquareWidth(){ return 0;};
    int getSquareHeight(){ return 0;};

    int get( int where ){ return 0;};
    boolean fixed( int where ){return false;};
    boolean isFinished(){return false;};
    String getHint( int where ){return "";};

    void undo(){};
    int[] loadFile(){return new int[2];};
    void save(){};
    void set( int where,  int value ){};
    void sets ( int[] values ){};

    void restart(){};
    long getTime(){ return 0;};
}
