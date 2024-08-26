package utils;

public enum GameLevel {

    BASIC("Basic"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    ADVANCED_PRO("Advanced Pro")
    ;

    private final String text;

    /**
     * @param text
     */
    GameLevel(String text) {
        this.text = text;
    }

    public static GameLevel convertor(String val){
        GameLevel level = null;

        if(val.equals("Basic") || val.equals("0")){
            level = BASIC;
        }
        else if (val.equals("Intermediate") || val.equals("1")){
            level = INTERMEDIATE;
        }
        else if (val.equals("Advanced") || val.equals("2")) {
            level = ADVANCED;
        } else if (val.equals("Advanced Pro") || val.equals("3")) {
            level = ADVANCED_PRO;
        }
        return level;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
