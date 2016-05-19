package zach.christensen.everyonessudoku.Model;

import java.util.Set;

public interface hintPossible {
    public Set<Integer> rowPossibilities(int rowIndex);
    public Set<Integer> columnPossibilities( int columnIndex);
    public Set<Integer> squarePossibilities( int squareIndex);
}
