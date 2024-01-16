package kalah.Memento;

import kalah.GameData.GameBoardInfo;

public class Caretaker {

    private Memento memento = null;
    private GameBoardInfo gameboardinfo;

    public Caretaker(GameBoardInfo gameboardinfo) {
        this.gameboardinfo = gameboardinfo;
    }

    public Memento getMemento() {

        return memento;
    }

    public void setMemento() {
        this.memento = gameboardinfo.createMemento();
        gameboardinfo.setMemento(true);
    }
}
