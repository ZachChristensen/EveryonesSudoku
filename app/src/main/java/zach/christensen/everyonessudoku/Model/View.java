package zach.christensen.everyonessudoku.Model;

public interface View {
    void updateDisplay();
    <T> void print(T out);
}
