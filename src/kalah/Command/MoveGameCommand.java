package kalah.Command;

import kalah.GameData.GameBoardInfo;
import kalah.GameService.GameService;

public class MoveGameCommand implements Command{

    private GameBoardInfo gameboardinfo;
    private GameService gameservice;
    private String houseindex;

    public MoveGameCommand(GameBoardInfo gameboardinfo, GameService gameservice){
        this.gameboardinfo = gameboardinfo;
        this.gameservice = gameservice;
    }

    public MoveGameCommand setCommand(String houseindex){
        this.houseindex = houseindex;
        return this;
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
        gameservice.process(gameboardinfo, houseindex);
    }
    
}
