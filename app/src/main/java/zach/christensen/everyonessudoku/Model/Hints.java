package zach.christensen.everyonessudoku.Model;

import java.util.HashSet;
import java.util.Set;

class Hints implements hintPossible, hintImpossible{
    private GameSudoku myGame;
    Hints(GameSudoku newGame) {
        this.myGame = newGame;
    }


    Set<Integer> cellImpossibilities(int index) {
        int value = this.myGame.toArray()[index];
        this.myGame.setByIndex(0,index);
        int x = index % myGame.getMaxValue();
        int y = index / myGame.getMaxValue();
        Set<Integer> row = rowImpossibilities(y);
        Set<Integer> column = columnImpossibilities(x);
        Set<Integer> square = squareImpossibilities(myGame.indexToSquare(index)[0]);
        Set<Integer> result = new HashSet<>();
        result.addAll(row);
        result.addAll(column);
        result.addAll(square);
        this.myGame.setByIndex(value,index);
        return result;
    }

    Set<Integer> cellPossibilities(int index) {
        int max = this.myGame.getMaxValue();
        Set<Integer> impossible = cellImpossibilities(index);
        Set<Integer> possible = new HashSet<>();
        for (int i=0; i < max; i++){
            possible.add(i+1);
        }
        possible.removeAll(impossible);
        return possible;
    }

    public Set<Integer> rowImpossibilities(int rowIndex) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i=rowIndex*max; i < max+(rowIndex*max); i++){
            if(!(grid[i] == 0)) {
                set.add(grid[i]);
            }
        }
        return set;
    }

    public Set<Integer> columnImpossibilities(int columnIndex) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i= 0; i < max; i++){
            if(!(grid[columnIndex+(i*max)] == 0)) {
                set.add(grid[columnIndex+(i*max)]);
            }
        }
        return set;
    }

    public Set<Integer> squareImpossibilities(int squareIndex) {
        //What values in this square have been used?
        int max = myGame.getMaxValue();
        Set<Integer> set = new HashSet<>();
        int[] square = this.myGame.getSquares(squareIndex);
        for (int i=0; i < max; i++){
            if(!(square[i] == 0)) {
                set.add(square[i]);
            }
        }
        return set;
    }

    public Set<Integer> rowPossibilities(int rowIndex) {
        return null;
    }

    public Set<Integer> columnPossibilities(int columnIndex) {
        return null;
    }

    public Set<Integer> squarePossibilities(int squareIndex) {
        return null;
    }
}
