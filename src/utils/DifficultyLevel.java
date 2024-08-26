package utils;

public enum DifficultyLevel {
    EASY("Easy"),
    Easy("Easy"),
    MEDIUM("Medium"),
    HARD("Hard")
            ;

    private final String text;

    /**
     * @param text
     */
    DifficultyLevel(final String text) {
        this.text = text;
    }

    public static DifficultyLevel convertor(String val){
                DifficultyLevel level = null;

                if(val.equals("Easy") || val.equals("0")){
                    level = EASY;
                }
                else if (val.equals("Medium") || val.equals("1")){
                    level = MEDIUM;
                }
                else if (val.equals("Hard") || val.equals("2")) {
                    level = HARD;
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
