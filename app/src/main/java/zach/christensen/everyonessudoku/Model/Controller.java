//package christensen.sudoku;
package zach.christensen.everyonessudoku.Model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import zach.christensen.everyonessudoku.SudokuGame9x9;

public class Controller {
    private GameSudoku myGameSudoku;
    private SudokuGame9x9 myView;

    public Controller(SudokuGame9x9 newView) {
        this.myView = newView;
        this.myGameSudoku = new GameSudoku(this);
    }

    public void loadTest9x9(){
        this.myGameSudoku.myDataHandler.testData3x3();
    }

    public void updateCellView(int index, int value){
        myView.updateCell(index, value);
    }

    public void updateCellModel(int index, int value){
        this.myGameSudoku.move(value,index);
        updateCellView(index, this.myGameSudoku.getByIndex(index));
    }

    public String getHint(int index){

        List<Integer> list = new ArrayList<>();
        list.addAll(myGameSudoku.myHinter.cellPossibilities(index));
        Collections.sort(list);
        return "Possible Numbers: " + list.toString();
    }

    public void restartGrid(){
        this.myGameSudoku.restart();
    }

    public void undoMove(){
        this.myGameSudoku.unmove();
    }

    public boolean isComplete(){
        return this.myGameSudoku.myValidator.isFinished();
    }

    public int[] getGrid(){
        return myGameSudoku.toArray();
    }

    void output(String s){
        this.myView.outputToast(s);
    }
}
