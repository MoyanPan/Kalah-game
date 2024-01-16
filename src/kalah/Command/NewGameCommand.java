package kalah.Command;

import kalah.GameData.GameBoardInfo;

public class NewGameCommand implements Command{

    private GameBoardInfo gameboardinfo;

    public NewGameCommand(GameBoardInfo gameboardinfo){
        this.gameboardinfo = gameboardinfo;
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        gameboardinfo.initGame();
    }
    
}
