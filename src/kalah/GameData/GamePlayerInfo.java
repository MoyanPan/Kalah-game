package kalah.GameData;

import java.util.HashMap;

public class GamePlayerInfo {
    private String playername;
    private HashMap<Integer ,Integer> houses;
    private Integer store;

    public String getName(){
        return this.playername;
    }

    public void setName(String name){
        this.playername = name;
    }

    public HashMap<Integer, Integer> getHouses(){
        return houses;
    }

    public void setHouses(HashMap<Integer, Integer> houses){
        this.houses = houses;
    }

    public Integer getStore(){
        return store;
    }

    public void setStore(Integer store){
        this.store = store;
    }

    public void setHouse(Integer index, Integer value){
        houses.put(index,value);
    }
    
    public Integer getHouse(Integer index){
        return houses.get(index);
    }

    public void add(Integer index, Integer seeds){
        houses = this.getHouses();
        houses.put(index, houses.get(index) + seeds);
        this.setHouses(houses);
    }
    

}
