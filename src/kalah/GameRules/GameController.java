package kalah.GameRules;


import kalah.Command.Command;
import kalah.Command.Invoker;
import kalah.Command.LoadGameCommand;
import kalah.Command.MoveGameCommand;
import kalah.Command.NewGameCommand;
import kalah.Command.QuitGameCommand;
import kalah.Command.SaveGameCommand;
import kalah.GameData.GameBoardInfo;
import kalah.GameData.GamePlayerInfo;
import kalah.GameService.GameService;
import kalah.Memento.Caretaker;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

public class GameController {
    GameService gameservice = new GameService();
    GameBoardInfo gameboardinfo =  new GameBoardInfo();

    private Invoker invoker = new Invoker();
    private Caretaker caretaker = new Caretaker(gameboardinfo);
    private LoadGameCommand loadgamecommand = new LoadGameCommand(caretaker, gameboardinfo);
    private NewGameCommand newgamecommand = new NewGameCommand(gameboardinfo);
    private QuitGameCommand quitgamecommand = new QuitGameCommand(gameboardinfo);
    private SaveGameCommand savegamecommand = new SaveGameCommand(caretaker);
    private MoveGameCommand movegamecommand = new MoveGameCommand(gameboardinfo, gameservice);


    private String houseindex;
    public void start(IO io){
        gameboardinfo.initGame();
        gameservice.printBoard(gameboardinfo, io);
    
        while(gameRunningStill(gameboardinfo) == true){
            if(gameservice.gameOver(gameboardinfo,  io)){
                break;
            }else{
                houseindex = gameservice.askForInt(gameboardinfo, io);
                switch(houseindex){
                case "1": case "2": case "3": case "4": case "5": case "6":
                if(gameservice.isEmpMove(gameboardinfo,houseindex)){
                    gameservice.moveAgain(io);
                    invoker.setCommand(movegamecommand.setCommand("-1"));
                    break;
                }else{
                    invoker.setCommand(movegamecommand.setCommand(houseindex));
                    break;
                }
                case "n": case"N":
                    invoker.setCommand(newgamecommand);
                    break;
                case "s": case"S":
                    invoker.setCommand(savegamecommand);
                    break;
                case "l": case"L":
                    invoker.setCommand(loadgamecommand);
                    break;
                case "q": case "Q":
                    invoker.setCommand(quitgamecommand);
                    break;
                }
                invoker.execute();
                endRoundProcess(io, gameboardinfo, houseindex);

            }
        }
        gameSummary(gameboardinfo, io, gameservice.gameOver(gameboardinfo, io));
    }

    public void endRoundProcess(IO io, GameBoardInfo gameboardinfo, String houseindex){
        backedUpDataCheck(io,gameboardinfo, houseindex);
        if(gameRunningStill(gameboardinfo)){
            gameservice.printBoard(gameboardinfo, io);
        }
    }

    public void gameSummary(GameBoardInfo gameboardinfo, IO io, boolean gameover){
        gameboardinfo.setWinner();
        gameservice.end(io);
        if(gameover){
            gameservice.printBoard(gameboardinfo, io);
            gameservice.printSummary(gameboardinfo, io);
        }else if(!gameover){
            gameservice.printBoard(gameboardinfo, io);
        }
    }


    public boolean gameRunningStill(GameBoardInfo gameboardinfo){
        boolean running = true;
        if(gameboardinfo.getWinner() != null){
            running = false;
        }
        return running;
    }

    public void backedUpDataCheck(IO io, GameBoardInfo gameboardinfo, String houseindex){
        if(!gameboardinfo.getMemento() && (houseindex.equals("L") || houseindex.equals("l"))){
            io.println("No saved game");
        }
    }
}

