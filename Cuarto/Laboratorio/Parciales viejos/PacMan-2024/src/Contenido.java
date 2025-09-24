public enum Contenido {
    WALL(1), FOOD(2), EMPTY(3);
    private int code;

    Contenido(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
