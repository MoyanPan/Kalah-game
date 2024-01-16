package kalah.Memento;

import java.util.ArrayList;
import java.util.HashMap;

import kalah.GameData.GameBoardInfo;
import kalah.GameData.GamePlayerInfo;

public class Memento {
    private ArrayList<GamePlayerInfo> players;
    private String currentplayer;
    private String nextplayer;
    private Integer playernumber;
    private Integer houseseeds;
    private Integer housenumber;
    private Integer initstore;
    private String currenthouseindex;
    private Integer P1Store;
    private Integer P2Store;

    public Memento(GameBoardInfo gameboardinfo1){
        this.currentplayer = gameboardinfo1.getCurrentPlayer();
        this.nextplayer = gameboardinfo1.getNextPlayer();
        this.playernumber = gameboardinfo1.getPlayerNumber();
        this.houseseeds = gameboardinfo1.getHouseSeeds();
        this.housenumber = gameboardinfo1.getHouseNumber();
        this.initstore = gameboardinfo1.getInitStore();
        this.currenthouseindex = gameboardinfo1.getCurrentHouseIndex();
        this.P1Store = gameboardinfo1.getPlayers().get(0).getStore();
        this.P2Store = gameboardinfo1.getPlayers().get(1).getStore();
        ArrayList<GamePlayerInfo> PlayerList = new ArrayList<GamePlayerInfo>();
        for (int i = 1; i < 3; i++){
            GamePlayerInfo player = new GamePlayerInfo();
            player.setName("P" +  i);
            HashMap<Integer, Integer> houses = new HashMap<>();
            for(int x = 1; x < 7; x++){
                houses.put(x, gameboardinfo1.getPlayers().get(i-1).getHouse(x));
            }
            player.setHouses(houses);
            PlayerList.add(player);
        }
        this.players = PlayerList;
    }
    public GameBoardInfo getState(){
        GameBoardInfo gameboardinfo = new GameBoardInfo();
        gameboardinfo.setPlayers(this.players);
        gameboardinfo.setCurrentPlayer(currentplayer);
        gameboardinfo.setNextPlayer(nextplayer);
        gameboardinfo.setPlayerNumber(playernumber);
        gameboardinfo.setHouseSeeds(houseseeds);
        gameboardinfo.setHouseNumber(housenumber);
        gameboardinfo.setInitStore(initstore);
        gameboardinfo.setCurrentHouseIndex(currenthouseindex);
        gameboardinfo.getPlayers().get(0).setStore(P1Store);
        gameboardinfo.getPlayers().get(1).setStore(P2Store);
        return gameboardinfo;
    }
}
