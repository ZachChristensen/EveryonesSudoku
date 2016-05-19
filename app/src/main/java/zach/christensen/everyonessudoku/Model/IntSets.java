package zach.christensen.everyonessudoku.Model;

public interface IntSets {
    public void setByColumn(int value, int columnIndex, int rowIndex);
    public void setByRow(int value, int rowIndex, int columnIndex);
    public void setBySquare(int value, int squareIndex, int positionIndex);
}

