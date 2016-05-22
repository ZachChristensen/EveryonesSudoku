//package christensen.sudoku;
package zach.christensen.everyonessudoku.Model;

import android.app.Activity;

import zach.christensen.everyonessudoku.SudokuGame9x9;

public class Controller {
    private GameSudoku myGameSudoku;
    private SudokuGame9x9 myView;

    public Controller(SudokuGame9x9 newView) {
        this.myView = newView;
        this.myGameSudoku = new GameSudoku(this);
        this.myView.outputToast("HELLO WORLD");
    }

    public void loadTest9x9(){
        this.myGameSudoku.myDataHandler.testData3x3();
    }

    public void updateGrid(int[] grid){

    }

    public int[] getGrid(){
        return myGameSudoku.toArray();
    }

    void output(String s){
        this.myView.outputToast(s);
    }
}
