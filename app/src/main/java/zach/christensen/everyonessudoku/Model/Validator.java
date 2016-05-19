package zach.christensen.everyonessudoku.Model;

import java.util.HashSet;
import java.util.Set;

class Validator implements Validating {
    private GameSudoku myGame;

    Validator(GameSudoku myGame) {
        this.myGame = myGame;
    }

    public boolean isCompleted() {
        int max = myGame.getMaxValue();
        boolean col;
        boolean row;
        boolean sqr;
        for (int i=0; i < max; i++){
            col = isColumnComplete(i);
            row = isRowComplete(i);
            sqr = isSquareComplete(i);
            if(!(col && row && sqr)){
                return false;
            }
        }
        return true;
    }

    public boolean isLegal() {
        int max = myGame.getMaxValue();
        boolean col;
        boolean row;
        boolean sqr;
        for (int i=0; i < max; i++){
            col = isColumnLegal(i);
            row = isRowLegal(i);
            sqr = isSquareLegal(i);
            if(!(col && row && sqr)){
                return false;
            }
        }
        return true;
    }

    public boolean isFinished() {
        return isCompleted() && isLegal();
    }

    private boolean isRowComplete(int rowNum) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i=rowNum*max; i < max+(rowNum*max); i++){
            if(!(grid[i] == 0)) {
                if (!set.add(grid[i])) {
                    return false;
                }
            }
        }
        return set.size() == max;
    }

    private boolean isColumnComplete(int columnNum) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i < max; i++){
            if(!(grid[columnNum+(i*max)] == 0)) {
                if (!set.add(grid[columnNum+(i*max)])) {
                    return false;
                }
            }
        }
        return set.size() == max;
    }

    private boolean isSquareComplete(int squareNum) {
        int[] square = this.myGame.getSquares(squareNum);
        int max = myGame.getMaxValue();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i < max; i++){
            if(!(square[i] == 0)) {
                if (!set.add(square[i])) {
                    return false;
                }
            }
        }
        return set.size() == max;
    }
    private boolean isRowLegal(int rowNum) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i=rowNum*max; i < max+(rowNum*max); i++){
            if(!(grid[i] == 0)) {
                if (!set.add(grid[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isColumnLegal(int columnNum) {
        int max = myGame.getMaxValue();
        int[] grid = myGame.toArray();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i < max; i++){
            if(!(grid[columnNum+(i*max)] == 0)) {
                if (!set.add(grid[columnNum+(i*max)])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSquareLegal(int squareNum) {
        int[] square = this.myGame.getSquares(squareNum);
        int max = myGame.getMaxValue();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i < max; i++){
            if(!(square[i] == 0)) {
                if (!set.add(square[i])) {
                    return false;
                }
            }
        }
        return true;
    }


}
