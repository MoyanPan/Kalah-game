package kalah.Command;

import kalah.GameData.GameBoardInfo;
import kalah.Memento.Caretaker;

public class SaveGameCommand implements Command{

    private Caretaker caretaker;

    public SaveGameCommand(Caretaker caretaker){
        this.caretaker =  caretaker;
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        caretaker.setMemento();
    }
    
}
