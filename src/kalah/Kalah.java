package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;


import kalah.GameData.GameBoardInfo;
import kalah.GameService.GameService;
import kalah.GameRules.GameController;
/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	private GameController controller = new GameController();
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		controller.start(io);
	}
	public void play(IO io, boolean vertical, boolean bmf) {
		// DO NOT CHANGE. Only here for backwards compatibility
	        play(io);
	}
}
