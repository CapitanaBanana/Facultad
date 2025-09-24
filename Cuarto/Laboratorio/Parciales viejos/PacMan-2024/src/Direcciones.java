public enum Direcciones {
    ARRIBA(0, -1),
    ABAJO(0,1),
    IZQUIERDA(-1,0),
    DERECHA(1,0);
    private int posX;
    private int posY;
    Direcciones(int posX, int posY){
        this.posX=posX;
        this.posY=posY;
    }
    public int dx() { return posX; }
    public int dy() { return posY; }
}
