//package christensen.sudoku;
package zach.christensen.everyonessudoku.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zach.christensen.everyonessudoku.InterfaceSudokuGame;
import zach.christensen.everyonessudoku.SudokuGame;

public class Controller {
    private GameSudoku myGameSudoku;
    private InterfaceSudokuGame myView;

    public Controller(InterfaceSudokuGame newView) {
        this.myView = newView;
        this.myGameSudoku = new GameSudoku(this);
    }

    public void loadTest9x9(){
        this.myGameSudoku.myDataHandler.testData3x3();
    }

    public void loadTest6x6(){
        this.myGameSudoku.myDataHandler.testData3x2();
    }

    public void loadTest4x4(){
        this.myGameSudoku.myDataHandler.testData2x2();
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
