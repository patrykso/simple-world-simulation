package pl.edu.pg.po.projekt2;
import pl.edu.pg.po.projekt2.rosliny.*;
import pl.edu.pg.po.projekt2.zwierzeta.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Swiat {
    private int x, y;
    private int tura, liczbaOrganizmow;
    private ArrayList<Organizm> listaOrganizmow;
    private boolean czlowiekWykonalRuch;
    private boolean rozmnazanie, rozprzestrzenianie;
    private Czlowiek czlowiek;
    protected Organizm[][] plansza;

    public Swiat() {
        new Swiat(20, 20);
    }

    public Swiat(int x, int y) {
        this.x = x;
        this.y = y;
        tura = 0;
        liczbaOrganizmow = 0;
        czlowiekWykonalRuch = false;
        czlowiek = null;
        rozmnazanie = true;
        rozprzestrzenianie = true;
        this.listaOrganizmow = new ArrayList<Organizm>();

        plansza = new Organizm[20][20];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                plansza[i][j] = null;
            }
        }
    }

    private Punkt losujWolnePole() {
        Random rand = new Random();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; i++) {
                if (plansza[i][j] == null) {
                    while (true) {
                        int losX = rand.nextInt(x);
                        int losY = rand.nextInt(y);
                        return new Punkt(losX, losY);
                    }
                }
            }
        }
        return new Punkt(-1, -1);
    }

    void generuj() {
        setCzlowiekWykonalRuch(false);
        liczbaOrganizmow = (int) (this.x * this.y * 0.2);
        Punkt pole = losujWolnePole();
        if (pole.getX() == -1 || pole.getY() == -1) return;
        else {
            Czlowiek czlowiek = new Czlowiek(this, pole, this.tura);
            this.czlowiek = czlowiek;
            dodajOrganizm(czlowiek);
        }

        for (int i = 0; i < liczbaOrganizmow - 1; i++) {
            pole = losujWolnePole();
            if (pole.getX() == -1 || pole.getY() == -1) return;
            else dodajOrganizm(wylosujOrganizm(pole));
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private Organizm wylosujOrganizm(Punkt pole) {
        Random rand = new Random();
        int los = rand.nextInt(10);
        if(los == 0) return new Wilk(this, pole, this.tura);
        else if(los == 1) return new Owca(this, pole, this.tura);
        else if(los == 2) return new Lis(this, pole, this.tura);
        else if(los == 3) return new Zolw(this, pole, this.tura);
        else if(los == 4) return new Antylopa(this, pole, this.tura);
        else if(los == 5) return new Trawa(this, pole, this.tura);
        else if(los == 6) return new Mlecz(this, pole, this.tura);
        else if(los == 7) return new Guarana(this, pole, this.tura);
        else if(los == 8) return new WilczeJagody(this, pole, this.tura);
        else return new BarszczSosnowskiego(this, pole, this.tura);
    }

    public void dodajOrganizm(Organizm organizm) {
        listaOrganizmow.add(organizm);
        plansza[organizm.getPole().getY()][organizm.getPole().getX()] = organizm;
    }

    public void sortujOrganizmy() {
        Collections.sort(listaOrganizmow);
    }

    public boolean koniecGry() {
        if(czlowiek.czyZyje()) return false;
        else return true;
    }

    public Czlowiek getCzlowiek() {
        return this.czlowiek;
    }

    public void wykonajTure() {
        Komentarz.czyscKomentarze();
        sortujOrganizmy();
        czlowiek.zmniejszCooldownUmiejetnosci();
        if(czlowiek.getSila() > 5) czlowiek.zmniejszSile();
        tura++;
        for (int i = 0; i < listaOrganizmow.size(); i++) {
            if(listaOrganizmow.get(i) == null) continue;
            if (listaOrganizmow.get(i).czyZyje()) {
                if(listaOrganizmow.get(i) == czlowiek) {
                    if (!getCzlowiekWykonalRuch()) {
                        setCzlowiekWykonalRuch(true);
                    }
                    continue;
                }
                else listaOrganizmow.get(i).akcja(listaOrganizmow.get(i).getPole());
            }
        }
        setCzlowiekWykonalRuch(false);
        if(koniecGry()) System.exit(4);
    }

    public boolean czyPoleZajete(Punkt pole) {
        if(plansza[pole.getY()][pole.getX()] == null) return false;
        else return true;
    }

    public Organizm getPole(Punkt pole) {
        return plansza[pole.getY()][pole.getX()];
    }

    public void przemiescOrganizm(Organizm organizm, Punkt nowePole) {
        plansza[organizm.getPole().getY()][organizm.getPole().getX()] = null;
        plansza[nowePole.getY()][nowePole.getX()] = organizm;
        organizm.setPole(nowePole);
    }

    public void zabijOrganizm(Organizm organizm) {
        organizm.setUmarl();
        Punkt pole = organizm.getPole();
        plansza[pole.getY()][pole.getX()] = null;
    }

    public boolean silniejszyOrganizm(Punkt pole, int inicjatywa) {
        if(plansza[pole.getY()][pole.getX()] == null) return false;
        else if(plansza[pole.getY()][pole.getX()].getInicjatywa() >= inicjatywa) return true;
        return false;
    }

    public void setCzlowiekWykonalRuch(boolean state) {
        this.czlowiekWykonalRuch = state;
    }

    public boolean getCzlowiekWykonalRuch() {
        return czlowiekWykonalRuch;
    }

    public int getTura() {
        return this.tura;
    }

    public boolean getZezwolNaRozmnazanie() {
        return rozmnazanie;
    }

    public boolean getZezwolNaRozprzestrzenianie() {
        return rozprzestrzenianie;
    }

    public Organizm[][] getPlansza() {
        return plansza;
    }

    public void zapiszSwiat(String nazwaPliku) {
        try {
            nazwaPliku += ".txt";
            File plik = new File(nazwaPliku);
            plik.createNewFile();
            PrintWriter writer = new PrintWriter(plik);
            writer.print(tura + "\n");

            for(int i = 0; i < listaOrganizmow.size(); i++) {
                if (listaOrganizmow.get(i).nazwaOrganizmu() == "Barszcz Sosnowskiego") {
                    writer.print("BarszczSosnowskiego ");
                }
                else if (listaOrganizmow.get(i).nazwaOrganizmu() == "Wilcze Jagody") {
                    writer.print("WilczeJagody ");
                }
                else {
                    writer.print(listaOrganizmow.get(i).nazwaOrganizmu() + " ");
                }
                writer.print(listaOrganizmow.get(i).getPole().getX() + " ");
                writer.print(listaOrganizmow.get(i).getPole().getY() + " ");
                writer.print(listaOrganizmow.get(i).getSila() + " ");
                writer.print(listaOrganizmow.get(i).getTuraUrodzenia() + " ");
                writer.print(listaOrganizmow.get(i).czyZyje() + " ");
                if (listaOrganizmow.get(i) instanceof Czlowiek) {
                    writer.print(((Czlowiek) listaOrganizmow.get(i)).getCooldown());
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static Swiat wczytajSwiat(String nazwaPliku) {
        try {
            nazwaPliku += ".txt";
            File plik = new File(nazwaPliku);
            Scanner scanner = new Scanner(plik);
            String linia = scanner.nextLine();
            String[] p = linia.split(" ");
            Swiat swiat = new Swiat(20, 20);
            swiat.tura = Integer.parseInt(p[0]);

            while(scanner.hasNextLine()) {
                linia = scanner.nextLine();
                p = linia.split(" ");
                String nazwaOrganizmu = p[0];
                int x = Integer.parseInt(p[1]);
                int y = Integer.parseInt(p[2]);
                int sila = Integer.parseInt(p[3]);
                int turaUrodzenia = Integer.parseInt(p[4]);
                boolean czyZyje = Boolean.parseBoolean(p[5]);

                if (Objects.equals(nazwaOrganizmu, "Czlowiek")) {
                    int cooldown = Integer.parseInt(p[6]);
                    Czlowiek tmpOrganizm = new Czlowiek(swiat, new Punkt(x, y), turaUrodzenia);
                    tmpOrganizm.setCooldown(cooldown);
                    if(!czyZyje) tmpOrganizm.setUmarl();
                    swiat.czlowiek = tmpOrganizm;
                    swiat.dodajOrganizm(tmpOrganizm);
                }
                else {
                    Organizm tmpOrganizm;
                    if (Objects.equals(nazwaOrganizmu, "BarszczSosnowskiego") && czyZyje) tmpOrganizm = new BarszczSosnowskiego(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Guarana") && czyZyje) tmpOrganizm = new Guarana(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Mlecz") && czyZyje) tmpOrganizm = new Mlecz(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Trawa") && czyZyje) tmpOrganizm = new Trawa(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "WilczeJagody") && czyZyje) tmpOrganizm = new WilczeJagody(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Antylopa") && czyZyje) tmpOrganizm = new Antylopa(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Lis") && czyZyje) tmpOrganizm = new Lis(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Owca") && czyZyje) tmpOrganizm = new Owca(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Wilk") && czyZyje) tmpOrganizm = new Wilk(swiat, new Punkt(x, y), turaUrodzenia);
                    else if (Objects.equals(nazwaOrganizmu, "Zolw") && czyZyje) tmpOrganizm = new Zolw(swiat, new Punkt(x, y), turaUrodzenia);
                    else continue;
                    tmpOrganizm.setSila(sila);
                    swiat.dodajOrganizm(tmpOrganizm);
                }
            }
            scanner.close();
            return swiat;
        } catch(IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public Punkt getPoleCzlowieka() {
        return czlowiek.getPole();
    }
}
