package pl.edu.pg.po.projekt2;
import java.util.Random;

public abstract class Organizm implements Comparable<Organizm> {
    protected int sila, inicjatywa, turaPowstania;
    protected char symbol;
    protected Swiat swiat;
    protected Punkt pole;
    protected boolean umarl;

    public abstract void kolizja(Organizm innyOrganizm);
    public abstract void akcja(Punkt pole);
    public abstract String nazwaOrganizmu();

    protected Punkt losujPunktWZasiegu(Punkt pole, int zasieg) {
        Random rand = new Random();
        int los = rand.nextInt(100);
        if (los <= 25) {
            return new Punkt(pole.getX() + zasieg, pole.getY());
        }
        else if (los <= 50) {
            return new Punkt(pole.getX(), pole.getY() + zasieg);
        }
        else if (los <= 75) {
            return new Punkt(pole.getX() - zasieg, pole.getY());
        }
        else if (los <= 100) {
            return new Punkt(pole.getX(), pole.getY() - zasieg);
        }
        else return new Punkt(-4, -4);
    }

    public Organizm(Swiat swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol) {
        this.swiat = swiat;
        this.pole = pole;
        this.turaPowstania = turaPowstania;
        this.sila = sila;
        this.symbol = symbol;
        this.inicjatywa = inicjatywa;
        this.umarl = false;
    }

    public int getInicjatywa() {
        return this.inicjatywa;
    }

    public void setPole(Punkt nowePole) {
        this.pole = nowePole;
    }

    public Punkt getPole() {
        return new Punkt(pole.getX(), pole.getY());
    }

    public int getSila() {
        return this.sila;
    }

    public void setUmarl() {
        this.umarl = true;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean czyZyje() {
        if(umarl) return false;
        else return true;
    }

    public int getTuraUrodzenia() {
        return turaPowstania;
    }

    @Override
    public int compareTo(Organizm o1) {
        int inicjatywa = o1.getInicjatywa();
        return this.inicjatywa - inicjatywa;
    }
}
