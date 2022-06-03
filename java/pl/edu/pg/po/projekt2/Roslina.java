package pl.edu.pg.po.projekt2;
import pl.edu.pg.po.projekt2.rosliny.*;
import java.util.Random;

public abstract class Roslina extends Organizm {
    public Roslina(Swiat swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
    }

    @Override
    public void akcja(Punkt pole) {
        Random rand = new Random();
        int los = rand.nextInt(100);
        if (los <= 5 && swiat.getZezwolNaRozprzestrzenianie()) {
            Punkt poleNowegoOrganizmu = new Punkt(-2, -2);
            while(true) {
                poleNowegoOrganizmu = losujPunktWZasiegu(this.getPole(), 1);
                if (poleNowegoOrganizmu.getY() >= swiat.getY() || poleNowegoOrganizmu.getY() < 0) continue;
                else if (poleNowegoOrganizmu.getX() >= swiat.getX() || poleNowegoOrganizmu.getX() < 0) continue;
                else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
                else break;
            }
            if(swiat.getPlansza()[poleNowegoOrganizmu.getY()][poleNowegoOrganizmu.getX()] == null) {
                swiat.dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
                Komentarz.dodajKomentarz(this.nazwaOrganizmu() + " rozprzestrzenia sie");
            }
        }
    }

    @Override
    public void kolizja(Organizm innyOrganizm) {
        if(this.sila > innyOrganizm.getSila()) {
            swiat.zabijOrganizm(this);
            swiat.zabijOrganizm(innyOrganizm);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " zjada " + this.nazwaOrganizmu() + " i ginie");
        }
        else {
            Punkt tmpPole = this.getPole();
            swiat.zabijOrganizm(this);
            swiat.przemiescOrganizm(innyOrganizm, tmpPole);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " zjada " + this.nazwaOrganizmu());
        }
    }

    protected Organizm getTypRozmnozonegoOrganizmu(Punkt pole) {
        if(this.nazwaOrganizmu() == "Trawa") return new Trawa(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Mlecz") return new Mlecz(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Guarana") return new Guarana(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Wilcze Jagody") return new WilczeJagody(swiat, pole, swiat.getTura());
        else return new BarszczSosnowskiego(swiat, pole, swiat.getTura());
    }

    public abstract String nazwaOrganizmu();
}
