package zach.christensen.everyonessudoku.Model;

class ViewConsole implements View{
    private Controller myController;
    ViewConsole(Controller newCont) {
        this.myController = newCont;
    }

    public void updateDisplay(){
        int[] arr = myController.getGrid();
        int max = (int)Math.sqrt(arr.length);
        for(int i = 0; i < arr.length; i++){
            if(i % max == 0){
                System.out.println();
                System.out.print("|");
            }
            System.out.print(Integer.toString(arr[i]) + "|");
        }
    }

    public <T> void print(T out){
        System.out.println(out);
    }
}
