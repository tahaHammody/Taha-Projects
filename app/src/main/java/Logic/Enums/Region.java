package Logic.Enums;

public enum Region {
    North, South, East, West;


    public static Region getRegionByName(String region){
    if(region.equals(North)){
        return North;
    }
    if(region.equals(South)){
            return South;
        }
    if(region.equals(East)){
        return East;
    }
    else{
        return West;
    }
    }
}
