package kalah.GameService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.GameData.GameBoardInfo;
import kalah.GameData.GamePlayerInfo;

public class GameService{
    public String askForInt(GameBoardInfo gameBoardInfo , IO io){
        io.println("Player " + gameBoardInfo.getCurrentPlayer());
        io.println("    (1-6) - house number for move");
        io.println("    N - New game");
        io.println("    S - Save game");
        io.println("    L - Load game");
        io.println("    q - Quit");
        return io.readFromKeyboard("Choice:");
    }

    public void printBoard(GameBoardInfo gameboardinfo, IO io) {
        ArrayList<GamePlayerInfo> players = gameboardinfo.getPlayers();
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.print(String.format("| %s |", players.get(1).getName()));
        List<Map.Entry<Integer, Integer>> reversedEntries = new ArrayList<>(players.get(1).getHouses().entrySet());
        Collections.reverse(reversedEntries);
        for(Map.Entry<Integer,Integer> house : reversedEntries){
            io.print(String.format(" %d[%2d] |", house.getKey(),house.getValue()));
        }
        io.println(String.format(" %2d |", players.get(0).getStore()));
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.print(String.format("| %2d |", players.get(1).getStore()));
        for(Map.Entry<Integer,Integer> house : players.get(0).getHouses().entrySet()){
            io.print(String.format(" %d[%2d] |", house.getKey(),house.getValue()));
        }
        io.println(String.format(" %s |", players.get(0).getName()));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public void nextRound(GameBoardInfo gameBoardInfo){
        gameBoardInfo.setCurrentPlayer(gameBoardInfo.getNextPlayer());
        gameBoardInfo.setNextPlayer(gameBoardInfo.getCurrentPlayer().equals("P1") ? "P2" : "P1");
        gameBoardInfo.setCurrentHouseIndex(gameBoardInfo.getCurrentPlayer() + ":" + "1");
    }

    public void process(GameBoardInfo gameBoardInfo, String houseindex){
        if(houseindex.equals("-1")){
            return;
        }
        GamePlayerInfo currentplayer = gameBoardInfo.getCurrPlayerObj();
        gameBoardInfo.setCurrentHouseIndex(gameBoardInfo.getCurrentPlayer() + ":" + houseindex);
        int seeds = currentplayer.getHouse(Integer.parseInt(houseindex));
        currentplayer.setHouse(Integer.parseInt(houseindex),0);
        for (int i = 1; i < seeds; i++){
            gameBoardInfo.move(1);
        }
        //DO LAST SEED CHECK HERE
        if(isExtraMove(gameBoardInfo)){
            extraMove(gameBoardInfo);
        }else{
            int index = Integer.parseInt(gameBoardInfo.getCurrentHouseIndex().split(":")[1]);
            if (ownSideCapture(gameBoardInfo, index)) {
                this.capture(gameBoardInfo);
            }else if(sideSwapCapture(currentplayer, gameBoardInfo, index)){
                gameBoardInfo.move(0);
                captureByIndex(gameBoardInfo);
            }
            else{
                gameBoardInfo.move(1);
            }
            this.nextRound(gameBoardInfo);
        }
    }

    public boolean ownSideCapture(GameBoardInfo gameBoardInfo, int index){
        boolean result = false;
        if(this.ownSide(gameBoardInfo) && index > 0 && isEmpMove(gameBoardInfo, Integer.toString(index + 1)) && oppSeedCheck(gameBoardInfo, index)){
            result = true;
        }   
        return result;
    }

    public boolean sideSwapCapture(GamePlayerInfo currentplayer, GameBoardInfo gameBoardInfo, int index){
        boolean result = false;
        if(!currentplayer.getName().equals(gameBoardInfo.getCurrentHouseIndex().split(":")[0]) && index == 6 && currentplayer.getHouse(1) == 0){
            result = true;
        }
        return result;
    }

    public void extraMove(GameBoardInfo gameBoardInfo){
        gameBoardInfo.getCurrPlayerObj().setStore(gameBoardInfo.getCurrPlayerObj().getStore() + 1);
        this.anotherTurn(gameBoardInfo);
    }
    public boolean isExtraMove(GameBoardInfo gameBoardInfo){
        boolean result = false;
        if(this.inStore(gameBoardInfo) && this.ownSide(gameBoardInfo) && gameBoardInfo.getCurrPlayerObj().getName().equals(gameBoardInfo.getCurrentHouseIndex().split(":")[0])){
            result = true;
        }
        return result;
    }

    public void capture(GameBoardInfo gameBoardInfo) {
        int sum = 1;
        GamePlayerInfo currentplayer = gameBoardInfo.getCurrPlayerObj();
        GamePlayerInfo otherplayer = gameBoardInfo.getPlayers().get(0).getName().equals(currentplayer.getName()) ? gameBoardInfo.getPlayers().get(1) : gameBoardInfo.getPlayers().get(0);
        String[] currentindex = gameBoardInfo.getCurrentHouseIndex().split(":");
        int captureindex = Integer.parseInt(currentindex[1])+1;
        sum += currentplayer.getHouse(captureindex) + otherplayer.getHouse(gameBoardInfo.getHouseNumber() - captureindex +1);
        currentplayer.setHouse(captureindex, 0);
        otherplayer.setHouse(gameBoardInfo.getHouseNumber() - captureindex + 1, 0);
        currentplayer.setStore(currentplayer.getStore() + sum);
        }

    public void captureByIndex(GameBoardInfo gameBoardInfo){
        GamePlayerInfo currentplayer = gameBoardInfo.getCurrPlayerObj();
        GamePlayerInfo otherplayer = gameBoardInfo.getPlayers().get(0).getName().equals(currentplayer.getName()) ? gameBoardInfo.getPlayers().get(1) : gameBoardInfo.getPlayers().get(0);
        int sum = 1 + otherplayer.getHouse(6);
        currentplayer.setHouse(1, 0);
        otherplayer.setHouse(6, 0);
        currentplayer.setStore(currentplayer.getStore() + sum);
    }




    public void anotherTurn(GameBoardInfo gameBoardInfo) {
        gameBoardInfo.setCurrentPlayer(gameBoardInfo.getCurrentPlayer());
        gameBoardInfo.setNextPlayer(gameBoardInfo.getCurrentPlayer().equals("P1") ? "P2" : "P1");
    }


    public Boolean ownSide(GameBoardInfo gameBoardInfo){
        return gameBoardInfo.getCurrentHouseIndex().split(":")[0].equals(gameBoardInfo.getCurrPlayerObj().getName());
    }

    public Boolean inStore(GameBoardInfo gameBoardInfo){
        return (Integer.parseInt(gameBoardInfo.getCurrentHouseIndex().split(":")[1]) + 1 == 7);
    }

    public boolean oppSeedCheck(GameBoardInfo gameboardinfo, int houseindex ){
        GamePlayerInfo opplayer = gameboardinfo.getPlayers().get(0).getName().equals(gameboardinfo.getCurrentPlayer()) ?  gameboardinfo.getPlayers().get(1) : gameboardinfo.getPlayers().get(0);
        if(opplayer.getHouse(gameboardinfo.getHouseNumber() - houseindex) != 0){
            return true;
        }
        return false;
    }

    public boolean gameOver(GameBoardInfo gameboardinfo, IO io){
        GamePlayerInfo currentplayer = gameboardinfo.getCurrPlayerObj();
        int sum = 0;
        for(int j = 1; j < gameboardinfo.getHouseNumber() + 1; j++){
            sum += currentplayer.getHouse(j);
        }
        if(sum == 0){
            return true;
        }
        return false;
    }


    public boolean draw(GameBoardInfo gameboardinfo) {
        if(gameboardinfo.getPlayers().get(0).getStore() == gameboardinfo.getPlayers().get(1).getStore()){
            return true;
        }
        return false;
    }

    public void printSummary(GameBoardInfo gameboardinfo, IO io) {
        ArrayList<GamePlayerInfo> playerlist = gameboardinfo.getPlayers();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int x = 0; x < gameboardinfo.getPlayerNumber(); x++){
            int oneresult = 0;
            for(int i = 0; i < gameboardinfo.getHouseNumber(); i++){    
                oneresult += playerlist.get(x).getHouse(i+1);
            }
            result.add(oneresult + playerlist.get(x).getStore());
        }
        io.println("	player 1:" + result.get(0));
        io.println("	player 2:" + result.get(1));
        if( result.get(0) == result.get(1)){
            io.println("A tie!");
        }else if(result.get(0) > result.get(1)){
            io.println("Player 1 wins!");
        }else{
            io.println("Player 2 wins!");
        }
    }

    public void end(IO io){
        io.println("Game over");
    }

    public Boolean isEmpMove(GameBoardInfo gameboardinfo, String houseindex){
        if(gameboardinfo.getCurrPlayerObj().getHouse(Integer.parseInt(houseindex)) == 0){
            return true;
        }else{
            return false;
        }
    }

    public void moveAgain(IO io) {
        io.println("House is empty. Move again.");
    }

}


