package zach.christensen.everyonessudoku.Model;

public interface IntGets {
    public int getByColumn(int columnIndex, int rowIndex);
    public int getByRow(int rowIndex, int columnIndex);
    public int getBySquare(int squareIndex, int positionIndex);
}
