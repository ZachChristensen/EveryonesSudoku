package zach.christensen.everyonessudoku.Model;

import java.util.Set;

public interface hintImpossible {
    public Set<Integer> rowImpossibilities( int rowIndex);
    public Set<Integer> columnImpossibilities(int columnIndex);
    public Set<Integer> squareImpossibilities( int squareIndex);
}
