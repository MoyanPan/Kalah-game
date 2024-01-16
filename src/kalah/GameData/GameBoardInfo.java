package kalah.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import kalah.GameData.GamePlayerInfo;
import kalah.Memento.Memento;


public class GameBoardInfo {
    private ArrayList<GamePlayerInfo> players;
    private String currentplayer = "P2";
    private String nextplayer;
    private Integer playernumber = 2;
    private Integer houseseeds = 4;
    private Integer housenumber = 6;
    private Integer initstore = 0;
    private GamePlayerInfo winner = null;
    private String currenthouseindex;
    private Boolean memento = false;


    public String getCurrentHouseIndex(){
        return this.currenthouseindex;
    }

    public void setCurrentHouseIndex(String currenthouseindex){
        this.currenthouseindex = currenthouseindex;
    }

    public void setWinner(){
        this.winner =  this.getPlayers().get(0).getStore() > this.getPlayers().get(1).getStore() ? this.getPlayers().get(0) : this.getPlayers().get(1);
    }

    public GamePlayerInfo getWinner(){
        return winner;
    }

    public Integer getInitStore(){
        return this.initstore;
    }

    public void setInitStore(Integer store){
        this.initstore = store;
    }
    
    public ArrayList<GamePlayerInfo> getPlayers(){
        return this.players;
    }

    public void setPlayers(ArrayList<GamePlayerInfo> players){
        this.players = players;
    }

    public void setCurrentPlayer(String currentplayer){
        this.currentplayer = currentplayer;
    }

    public String getCurrentPlayer(){
        return this.currentplayer;
    }

    public void setNextPlayer(String player){
        this.nextplayer = player;
    }

    public String getNextPlayer(){
        return this.nextplayer;
    }

    public Integer getPlayerNumber(){
        return this.playernumber;
    }

    public void setPlayerNumber(Integer playernumber){
        this.playernumber = playernumber;
    }

    public Integer getHouseSeeds(){
        return this.houseseeds;
    }

    public Integer getHouseNumber(){
        return this.housenumber;
    }

    public void setHouseNumber(Integer housenumber){
        this.housenumber = housenumber;
    }

    public void setHouseSeeds(Integer houseseeds){
        this.houseseeds = houseseeds;
    }

    public GamePlayerInfo getCurrPlayerObj(){
        GamePlayerInfo player = this.getPlayers().get(0).getName().equals(getCurrentPlayer()) ?  this.getPlayers().get(0) : this.getPlayers().get(1);
        return player;

    }

    public void move(Integer seeds){
        GamePlayerInfo currentplayer = this.getCurrPlayerObj();
        players = this.getPlayers();
        String[] currenthouseinfo = getCurrentHouseIndex().split(":");
        if(currenthouseindex.split(":")[0].equals("P1")){
            try{
                players.get(0).add(Integer.parseInt(currenthouseinfo[1])+1 , seeds);
                setCurrentHouseIndex(currenthouseinfo[0] + ":" + (Integer.parseInt(currenthouseinfo[1])+1));
            }catch(Exception e){
                moveSide(currentplayer);
            }
        }else{
            try{
                players.get(1).add(Integer.parseInt(currenthouseinfo[1])+1 , seeds);
                setCurrentHouseIndex(currenthouseinfo[0] + ":" + (Integer.parseInt(currenthouseinfo[1])+1));
            }catch(Exception e){
                moveSide(currentplayer);
            }
        }

            
    }

    public void moveSide(GamePlayerInfo currentplayer){
        String currentside = this.getCurrentHouseIndex().split(":")[0];
        if(currentside.equals("P1")){
            setCurrentHouseIndex("P2:0");
        }else{
            setCurrentHouseIndex("P1:0");
        }
        if(currentside.equals(currentplayer.getName())){
            currentplayer.setStore(currentplayer.getStore()+1);
        }else{
            this.move(1);
        }
    }

    public void initGame(){
        ArrayList<GamePlayerInfo> PlayerList = new ArrayList<GamePlayerInfo>();
        for (int i = 1; i < this.getPlayerNumber()+1; i++){
            GamePlayerInfo player = new GamePlayerInfo();
            player.setName("P" +  i);
            HashMap<Integer, Integer> houses = new HashMap<>();
            for (int x = 0; x < this.getHouseNumber(); x++){
                houses.put(x+1, this.getHouseSeeds());
            }
            player.setHouses(houses);
            player.setStore(this.getInitStore());
            PlayerList.add(player);
        }
        this.setPlayers(PlayerList);
        this.setCurrentPlayer("P1");
        this.setNextPlayer("P2");
        this.memento = false;
    }

    public void Quitgame(){
        this.setWinner();
    }

    public Memento createMemento(){
        return new Memento(this);
    }


    public void restoreMemento(Memento memento){
        if(this.memento){
            this.setState(memento.getState());
        }

    }

    public void setMemento(boolean value){
        this.memento = value;
    }

    public Boolean getMemento(){
        return memento;
    }

    public void setState(GameBoardInfo gameboardinfo){
        this.players = gameboardinfo.getPlayers();
        this.currentplayer = gameboardinfo.getCurrentPlayer();
        this.nextplayer = gameboardinfo.getNextPlayer();
        this.playernumber = gameboardinfo.getPlayerNumber();
        this.houseseeds = gameboardinfo.getHouseSeeds();
        this.housenumber = gameboardinfo.getHouseNumber();
        this.initstore = gameboardinfo.getInitStore();
        this.winner = gameboardinfo.getWinner();
        this.currenthouseindex = gameboardinfo.getCurrentHouseIndex();
    }
}
