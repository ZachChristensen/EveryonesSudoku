package zach.christensen.everyonessudoku.Model;

import java.util.ArrayList;

class History {
	private ArrayList<Move> pastMoves;

	History() {
		pastMoves = new ArrayList<>();
	}

	void addMove(int newIndex, int oldValue, int newValue) {
		this.pastMoves.add(new Move(newIndex, oldValue, newValue));
	}


    Move removeMove() {
		/*removes most recent move from pastMoves and returns
		it to the Game so it can undo the move.*/
		return this.pastMoves.remove(this.pastMoves.size()-1);
	}
}
