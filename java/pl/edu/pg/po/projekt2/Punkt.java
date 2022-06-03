package pl.edu.pg.po.projekt2;

public class Punkt {
    private int x;
    private int y;

    public Punkt() {
        x = 0;
        y = 0;
    }

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
