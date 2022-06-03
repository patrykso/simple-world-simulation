package pl.edu.pg.po.projekt2;
import pl.edu.pg.po.projekt2.zwierzeta.*;

public abstract class Zwierze extends Organizm {
    public Zwierze(Swiat swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
    }

    @Override
    public void akcja(Punkt pole) {
        Punkt nowePole = new Punkt(-2, -2);
        while(true) {
            nowePole = losujPunktWZasiegu(pole, 1);
            if (nowePole.getY() >= swiat.getY() || nowePole.getY() < 0) continue;
            else if (nowePole.getX() >= swiat.getX() || nowePole.getX() < 0) continue;
            else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
            else break;
        }
        if (swiat.czyPoleZajete(nowePole)) {
            swiat.getPole(nowePole).kolizja(this);
        }
        else {
            swiat.przemiescOrganizm(this, nowePole);
        }
    }

    @Override
    public void kolizja(Organizm innyOrganizm) {
        if(innyOrganizm.nazwaOrganizmu() == this.nazwaOrganizmu() && swiat.getTura() + 1 > this.turaPowstania && swiat.getZezwolNaRozmnazanie()) {
            Punkt poleNowegoOrganizmu = new Punkt(-2, -2);
            while(true) {
                poleNowegoOrganizmu = losujPunktWZasiegu(this.getPole(), 1);
                if (poleNowegoOrganizmu.getY() >= swiat.getY() || poleNowegoOrganizmu.getY() < 0) continue;
                else if (poleNowegoOrganizmu.getX() >= swiat.getX() || poleNowegoOrganizmu.getX() < 0) continue;
                else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
                else break;
            }
            swiat.dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " rozmnaza sie");
        }
        else if(this.sila >= innyOrganizm.getSila()) {
            swiat.zabijOrganizm(innyOrganizm);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " atakuje " + this.nazwaOrganizmu() + " i przegrywa");
        }
        else {
            Punkt tmpPole = this.getPole();
            swiat.zabijOrganizm(this);
            swiat.przemiescOrganizm(innyOrganizm, tmpPole);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " atakuje " + this.nazwaOrganizmu() + " i wygrywa");
        }
    }

    protected Organizm getTypRozmnozonegoOrganizmu(Punkt pole) {
        if(this.nazwaOrganizmu() == "Wilk") return new Wilk(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Owca") return new Owca(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Lis") return new Lis(swiat, pole, swiat.getTura());
        else if(this.nazwaOrganizmu() == "Zolw") return new Zolw(swiat, pole, swiat.getTura());
        else return new Antylopa(swiat, pole, swiat.getTura());
    }

    public abstract String nazwaOrganizmu();
}
