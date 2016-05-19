package zach.christensen.everyonessudoku.Model;

class DataHandler implements Serialize {
    private GameSudoku myGame;
    DataHandler(GameSudoku newGame) {
        myGame = newGame;
    }



    void testData2x2() {
        int[] newArr = new int[16];
        for( int i : newArr){
            newArr[i]= 0;
        }
        this.myGame.setMaxValue(4);
        this.myGame.setSquareWidth(2);
        this.myGame.setSquareHeight(2);
        newArr[1] = 1;
        newArr[2] = 3;
        newArr[4] = 2;
        newArr[11] = 3;
        newArr[13] = 2;
        newArr[14] = 1;
        this.myGame.set(newArr);
    }

    void testData2x2Finished() {
        int[] newArr = new int[16];
        for( int i : newArr){
            newArr[i]= 0;
        }
        this.myGame.setMaxValue(4);
        this.myGame.setSquareWidth(2);
        this.myGame.setSquareHeight(2);
        newArr[0] = 4;
        newArr[1] = 1;
        newArr[2] = 3;
        newArr[3] = 2;
        newArr[4] = 2;
        newArr[5] = 3;
        newArr[6] = 4;
        newArr[7] = 1;
        newArr[8] = 1;
        newArr[9] = 4;
        newArr[10] = 2;
        newArr[11] = 3;
        newArr[12] = 3;
        newArr[13] = 2;
        newArr[14] = 1;
        newArr[15] = 4;
        this.myGame.set(newArr);
    }

    void testData3x2() {
        int[] newArr = new int[36];
        for( int i : newArr){
            newArr[i]= 0;
        }
        this.myGame.setMaxValue(6);
        this.myGame.setSquareWidth(3);
        this.myGame.setSquareHeight(2);
        newArr[1] = 1;
        newArr[2] = 6;
        newArr[4] = 5;
        newArr[10] = 6;
        newArr[11] = 2;
        newArr[17] = 6;
        newArr[18] = 3;
        newArr[24] = 5;
        newArr[25] = 3;
        newArr[31] = 4;
        newArr[33] = 5;
        newArr[34] = 2;
        this.myGame.set(newArr);
    }

    void testData3x3() {
        int[] newArr = new int[81];
        for( int i : newArr){
            newArr[i]= 0;
        }
        this.myGame.setMaxValue(9);
        this.myGame.setSquareWidth(3);
        this.myGame.setSquareHeight(3);
        for( int i = 0; i < 9; i++){
            newArr[i*9+i]= i;
        }
        this.myGame.set(newArr);
    }

    public void fromCSV(String csv) {

    }

    public String toCSV() {
        return null;
    }

    public void setCell(int value, int gridIndex) {
        myGame.setCell(value, gridIndex);
    }

    public int getCell(int gridIndex) {
        return myGame.toArray()[gridIndex];
    }
}
