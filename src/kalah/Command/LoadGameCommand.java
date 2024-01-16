package kalah.Command;

import kalah.GameData.GameBoardInfo;
import kalah.Memento.Caretaker;
import kalah.Memento.Memento;

public class LoadGameCommand implements Command{

    private Caretaker caretaker;
    private GameBoardInfo gameboardinfo;

    public LoadGameCommand(Caretaker caretaker, GameBoardInfo gameboardinfo){
        this.caretaker =  caretaker;
        this.gameboardinfo = gameboardinfo;
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        gameboardinfo.restoreMemento(caretaker.getMemento());
        
    }
    
}
