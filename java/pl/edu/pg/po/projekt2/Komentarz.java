package pl.edu.pg.po.projekt2;

public class Komentarz {
    private static String komentarz = "";

    public static String wyswietlKomentarze() {
        return komentarz;
    }

    public static void dodajKomentarz(String nowyKomentarz) {
        komentarz += nowyKomentarz + "\n";
    }

    public static void czyscKomentarze() {
        komentarz = "";
    }
}
