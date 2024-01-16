package kalah.Command;

import kalah.GameData.GameBoardInfo;

public class QuitGameCommand implements Command{

    private GameBoardInfo gameboardinfo;

    public QuitGameCommand(GameBoardInfo gameboardinfo){
        this.gameboardinfo = gameboardinfo;
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
       gameboardinfo.setWinner();
    }
    
}
