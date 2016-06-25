package zach.christensen.everyonessudoku;

public interface InterfaceSudokuGame {
    void updateGrid(int[] newGrid);
    void updateCell(int index, int value);
    void outputToast(Integer output);
    void outputToast(String output);
    void updateMoveCount(int newCount);
}
