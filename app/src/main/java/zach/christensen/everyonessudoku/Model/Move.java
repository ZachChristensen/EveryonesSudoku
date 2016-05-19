package zach.christensen.everyonessudoku.Model;

class Move {
	int index;
	int oldNum;
	int newNum;
	
	Move(int index, int oldNum, int newNum) {
		this.index = index;
		this.oldNum = oldNum;
		this.newNum = newNum;
	}
}
