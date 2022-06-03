package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Swiat;
import pl.edu.pg.po.projekt2.Zwierze;

public class Czlowiek extends Zwierze {
    private static final char symbol = 'C';
    private static final int inicjatywa = 4;
    private int cooldownUmiejetnosci;

    public Czlowiek(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 5, inicjatywa, symbol);
        this.sila = 5;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
        this.cooldownUmiejetnosci = 0;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Czlowiek";
    }

    @Override
    public void akcja(Punkt pole) {
        if(swiat.czyPoleZajete(pole)) {
            swiat.getPole(pole).kolizja(this);
        }
        else swiat.przemiescOrganizm(this, pole);
    }

    public void aktywujSpecjalnaUmiejetnosc() {
        this.setSila(10);
        this.cooldownUmiejetnosci = 10;
    }

    public void zmniejszCooldownUmiejetnosci() {
        if (cooldownUmiejetnosci > 0) this.cooldownUmiejetnosci--;
    }

    public void zmniejszSile() {
        if(sila > 5) this.sila--;
    }

    public int getCooldown() {
        return cooldownUmiejetnosci;
    }

    public void setCooldown(int cooldown) {
        this.cooldownUmiejetnosci = cooldown;
    }
}
