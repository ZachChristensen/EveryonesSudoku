//package christensen.sudoku;
package zach.christensen.everyonessudoku.Model;

class Controller {
    private GameSudoku myGameSudoku;
    private ViewConsole myView;

    Controller() {
        this.myView = new ViewConsole(this);
        this.myGameSudoku = new GameSudoku(this);
    }

    void inputLoop(){
        this.myGameSudoku.myDataHandler.testData2x2();
        myView.updateDisplay();

        //wait for input I guess?
    }

    int[] getGrid(){
        return myGameSudoku.toArray();
    }

    <T> void output(T output){
        myView.print(output);
    }
}
