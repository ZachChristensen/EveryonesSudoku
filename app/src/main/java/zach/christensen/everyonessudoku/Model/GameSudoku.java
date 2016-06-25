package zach.christensen.everyonessudoku.Model;

class GameSudoku implements Game, IntGets, IntSets{
    private Controller myController;
    DataHandler myDataHandler;
    Validator myValidator;
    Hints myHinter;
    private History myHistory;
    private int[] grid;
    boolean[] frozenGrid;
    private int[][] squares;
    int[] startingGrid;
    private int maxValue;
    int squareWidth;
    int squareHeight;
    int moveCount;


    GameSudoku(Controller newCont) {
        this.myController = newCont;
        this.myDataHandler = new DataHandler(this);
        this.myValidator = new Validator(this);
        this.myHistory = new History();
        this.myHinter = new Hints(this);
    }

    boolean move(int value, int index){
        //Returns true if move succeeds
        if (value > maxValue || value < 1){
            this.myController.output("Error number too big");
            return false;
        }
        if (this.frozenGrid[index]){
            this.myController.output("Number is unchangeable");
            return false;
        }
        this.myHistory.addMove(index, grid[index], value);
        setByIndex(value, index);
        this.moveCount++;
        updateViewMoveCount();
        return true;
    }

    void unmove(){
        if (moveCount > 0) {
            Move undo = this.myHistory.removeMove();
            this.grid[undo.index] = undo.oldNum;
            int[] squarePos = this.indexToSquare(undo.index);
            this.squares[squarePos[0]][squarePos[1]] = undo.oldNum;
            this.moveCount--;
            myController.updateCellView(undo.index, undo.oldNum);
        }
        else {
            this.myController.output("No moves to undo.");
        }
        updateViewMoveCount();
    }

    void updateViewMoveCount(){
        this.myController.updateViewMoveCount(this.moveCount);
    }

    public void setMaxValue(int maximum) {
        this.maxValue = maximum;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    void setCell(int value, int gridIndex){
        this.grid[gridIndex] = value;
    }

    public int[] toArray() {
        return grid;
    }

    public void set(int[] cellValues) {
        this.grid = cellValues;
        this.startingGrid = cellValues.clone();

        this.frozenGrid = new boolean[this.grid.length];
        for(int i = 0; i < this.grid.length; i++){
            this.frozenGrid[i] = this.grid[i] != 0;
        }
        setSquares();
    }

    private void setSquares(){
        this.squares = new int[maxValue][maxValue];
        int z;
        int index;
        for(int i = 0; i < this.maxValue; i++){
            z=0;
            for(int y = 0; y < this.squareHeight; y++){
                for(int x = 0; x < this.squareWidth; x++){
                    index = x+(maxValue*y)+(i*squareWidth)+ maxValue * (i / 2);
                    squares[i][z] = grid[index];
                    z++;
                }
            }
        }
    }

    public void setSquareWidth(int squareWidth) {
        this.squareWidth = squareWidth;
    }

    public void setSquareHeight(int squareHeight) {
        this.squareHeight = squareHeight;
    }

    public void restart() {
        set(startingGrid);
        moveCount = 0;
        updateViewMoveCount();
    }

    int[] getSquares(int squareNum) {
        return this.squares[squareNum];
    }

    public int getByColumn(int columnIndex, int rowIndex) {
        return grid[xyToIndex(rowIndex,columnIndex)];
    }

    public int getByRow(int rowIndex, int columnIndex) {
        return grid[xyToIndex(rowIndex,columnIndex)];
    }

    public int getBySquare(int squareIndex, int positionIndex) {
        return grid[squareToIndex(squareIndex, positionIndex)];
    }

    public int getByIndex(int index) {
        return grid[index];
    }

    public void setByColumn(int value, int columnIndex, int rowIndex) {
        //trying to avoid duplication.
        setByRow(value, rowIndex, columnIndex);
    }

    public void setByRow(int value, int rowIndex, int columnIndex) {
        int index = xyToIndex(rowIndex,columnIndex);
        grid[index] = value;
        int[] squareData = indexToSquare(index);
        this.squares[squareData[0]][squareData[1]] = value;
    }

    public void setBySquare(int value, int squareIndex, int positionIndex) {
        this.squares[squareIndex][positionIndex] = value;
        this.grid[this.squareToIndex(squareIndex, positionIndex)] = value;
    }

    void setByIndex(int value, int index) {
        grid[index] = value;
        int[] squareData = indexToSquare(index);
        this.squares[squareData[0]][squareData[1]] = value;
    }

    /**
     * Takes an index and returns an array of squareNum and squareIndex index.
     * @param index The square number(starts at 0).
     * @return int[] where i[0] is squareNum and i[1] is squareIndex.
     */
    int[] indexToSquare(int index) {
        //Returns an int array with 2 ints. First is squareNum, second is squareIndex.
        int[] arr = new int[2];
        int numSquaresWide = maxValue / squareWidth;
        int y = (index / maxValue);
        int x = index % maxValue;
        int sqrIndex = index%squareWidth + (y%squareHeight * squareWidth);
        int squareNum = (x / squareWidth) + (y / squareHeight)*numSquaresWide;
        arr[0] = squareNum;
        arr[1] = sqrIndex;
        return arr;
    }

    /**
     * Takes a squareNumber and squareIndex and returns a grid index.
     * @param squareNum The square number(starts at 0).
     * @param squareIndex The square index(0-max).
     * @return int index for use on the grid.
     */
    int squareToIndex(int squareNum, int squareIndex) {
        int y = squareIndex / squareWidth;
        return squareIndex % squareWidth + (maxValue * y)+(squareNum*squareWidth)+ maxValue * (squareNum / 2);
    }

    int xyToIndex(int y, int x) {
        return (y * maxValue) + x;
    }

    void freezeCell(int index) {
        this.frozenGrid[index] = true;
    }

    void unfreezeCell(int index) {
        this.frozenGrid[index] = false;
    }

    void unfreezeAllCell() {
        for(int i = 0; i < this.grid.length; i++){
            this.frozenGrid[i] = false;
        }
    }

    int getMoveCount(){
        return this.moveCount;
    }
}
