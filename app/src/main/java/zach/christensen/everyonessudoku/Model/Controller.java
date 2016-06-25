//package christensen.sudoku;
package zach.christensen.everyonessudoku.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zach.christensen.everyonessudoku.InterfaceSudokuGame;

public class Controller extends AbstractController{
    private GameSudoku myGameSudoku;
    private InterfaceSudokuGame myView;

    public Controller(InterfaceSudokuGame newView) {
        this.myView = newView;
        this.myGameSudoku = new GameSudoku(this);
    }

    @Override
    public int getMax() {
        return this.myGameSudoku.getMaxValue();
    }

    @Override
    public int getRowmax() {
        // totally pointless don't crowdsource interfaces in the future.
        return this.myGameSudoku.getMaxValue();
    }

    @Override
    public int getColumMax() {
        // totally pointless don't crowdsource interfaces in the future.
        return this.myGameSudoku.getMaxValue();
    }

    @Override
    public int getSquareWidth() {
        return this.myGameSudoku.squareWidth;
    }

    @Override
    public int getSquareHeight() {
        return this.myGameSudoku.squareHeight;
    }

    @Override
    public int get(int where) {
        return this.myGameSudoku.getByIndex(where);
    }

    @Override
    public boolean fixed(int where) {
        return this.myGameSudoku.frozenGrid[where];
    }

    @Override
    public boolean isFinished() {
        return this.myGameSudoku.myValidator.isFinished();
    }

    @Override
    public void undo() {
        this.myGameSudoku.unmove();
    }

    @Override
    public int[] loadFile() {
        return this.myGameSudoku.toArray();
    }

    @Override
    public void save() {
        //not implemented
    }

    @Override
    public void set(int where, int value) {
        super.set(where, value);
    }

    @Override
    public void sets(int[] values) {
        // totally pointless don't crowdsource interfaces in the future.
        this.myGameSudoku.set(values);
    }

    @Override
    public void restart() {
        this.myGameSudoku.restart();
    }

    @Override
    public long getTime() {
        //time is handled in the view
        return 0;
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

    void output(String s){
        this.myView.outputToast(s);
    }

    void updateViewMoveCount(int newCount){
        this.myView.updateMoveCount(newCount);
    }
}
