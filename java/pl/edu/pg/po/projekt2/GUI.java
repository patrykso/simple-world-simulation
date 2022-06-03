package pl.edu.pg.po.projekt2;

import pl.edu.pg.po.projekt2.zwierzeta.Czlowiek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.*;

public class GUI implements ActionListener, KeyListener {
    private JButton wczytajGre, nowaGra, zapiszGre, nowaTura;
    GridBagConstraints gbc;
    private JLabel informacje, informacje2;
    private JTextArea komentarze;
    private String komentarz, autor, tura;
    private JFrame frame;
    private JPanel siatka;
    private Swiat swiat;

    public GUI() {
        frame = new JFrame("Symulacja swiata");
        siatka = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        wczytajGre = new JButton("Wczytaj gre");
        nowaGra = new JButton("Nowa gra");
        informacje = new JLabel("Autor projektu: Patryk Sowinski-Toczek, s191711.", SwingConstants.CENTER);
        informacje2 = new JLabel("Gra konczy sie po smierci czlowieka. Ruch strzalkami, aktywacja specjalnej umiejetnosci - P", SwingConstants.CENTER);
        frame.add(wczytajGre);
        frame.add(nowaGra);
        frame.add(informacje);
        frame.add(informacje2);
        frame.addKeyListener(this);
        wczytajGre.addActionListener(this);
        nowaGra.addActionListener(this);
        frame.setLayout(new GridLayout(4, 1, 40, 40));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nowaGra) {
            swiat = new Swiat(20, 20);
            swiat.generuj();
            frame.remove(wczytajGre);
            frame.remove(nowaGra);
            frame.remove(informacje);
            frame.remove(informacje2);
            wystartujGre();
        }
        else if (e.getSource() == wczytajGre) {
            String nazwaPliku = JOptionPane.showInputDialog(frame, "Podaj nazwe pliku", "swiat");
            swiat = Swiat.wczytajSwiat(nazwaPliku);
            frame.remove(wczytajGre);
            frame.remove(nowaGra);
            frame.remove(informacje);
            frame.remove(informacje2);
            wystartujGre();
        }
        else if (e.getSource() == zapiszGre) {
            String nazwaPliku = JOptionPane.showInputDialog(frame, "Podaj nazwe pliku", "swiat");
            swiat.zapiszSwiat(nazwaPliku);
            Komentarz.dodajKomentarz("Swiat zostal zapisany");
            komentarz = Komentarz.wyswietlKomentarze();
            komentarze.setText(autor + tura + komentarz);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        else if (e.getSource() == nowaTura) {
            swiat.wykonajTure();
            odswiezGre();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (swiat != null && !swiat.getCzlowiekWykonalRuch()) {
            int keyCode = e.getKeyCode();
            Punkt poleCzlowieka = swiat.getPoleCzlowieka();
            swiat.sortujOrganizmy();
            if (keyCode == KeyEvent.VK_RIGHT) {
                if(poleCzlowieka.getX() + 1 < swiat.getX()) {
                    swiat.getPlansza()[poleCzlowieka.getY()][poleCzlowieka.getX()] = null;
                    poleCzlowieka = new Punkt(poleCzlowieka.getX() + 1, poleCzlowieka.getY());
                    swiat.getCzlowiek().setPole(poleCzlowieka);
                    swiat.getCzlowiek().akcja(poleCzlowieka);
                    swiat.setCzlowiekWykonalRuch(true);
                    odswiezGre();
                }
                else {
                    Komentarz.dodajKomentarz("Nie mozesz sie tu ruszyc");
                    odswiezGre();
                }
            }
            else if (keyCode == KeyEvent.VK_LEFT) {
                if(swiat.getPoleCzlowieka().getX() - 1 >= 0) {
                    swiat.getPlansza()[poleCzlowieka.getY()][poleCzlowieka.getX()] = null;
                    poleCzlowieka = new Punkt(poleCzlowieka.getX() - 1, poleCzlowieka.getY());
                    swiat.getCzlowiek().setPole(poleCzlowieka);
                    swiat.getCzlowiek().akcja(poleCzlowieka);
                    swiat.setCzlowiekWykonalRuch(true);
                    odswiezGre();
                }
                else {
                    Komentarz.dodajKomentarz("Nie mozesz sie tu ruszyc");
                    odswiezGre();
                }
            }
            else if (keyCode == KeyEvent.VK_UP) {
                if(swiat.getPoleCzlowieka().getY() - 1 >= 0) {
                    swiat.getPlansza()[poleCzlowieka.getY()][poleCzlowieka.getX()] = null;
                    poleCzlowieka = new Punkt(poleCzlowieka.getX(), poleCzlowieka.getY() - 1);
                    swiat.getCzlowiek().setPole(poleCzlowieka);
                    swiat.getCzlowiek().akcja(poleCzlowieka);
                    swiat.setCzlowiekWykonalRuch(true);
                    odswiezGre();
                }
                else {
                    Komentarz.dodajKomentarz("Nie mozesz sie tu ruszyc");
                    odswiezGre();
                }
            }
            else if (keyCode == KeyEvent.VK_DOWN) {
                if(swiat.getPoleCzlowieka().getY() + 1 < swiat.getY()) {
                    swiat.getPlansza()[poleCzlowieka.getY()][poleCzlowieka.getX()] = null;
                    poleCzlowieka = new Punkt(poleCzlowieka.getX(), poleCzlowieka.getY() + 1);
                    swiat.getCzlowiek().setPole(poleCzlowieka);
                    swiat.getCzlowiek().akcja(poleCzlowieka);
                    swiat.setCzlowiekWykonalRuch(true);
                    odswiezGre();
                }
                else {
                    Komentarz.dodajKomentarz("Nie mozesz sie tu ruszyc");
                    odswiezGre();
                }
            }
            else if (keyCode == KeyEvent.VK_P) {
                if (swiat.getCzlowiek().getCooldown() == 0) {
                    Komentarz.dodajKomentarz("Aktywowales specjalna umiejetnosc czlowieka");
                    swiat.getCzlowiek().aktywujSpecjalnaUmiejetnosc();
                    odswiezGre();
                }
                else Komentarz.dodajKomentarz("Nie mozesz aktywowac specjalnej umiejetnosci");
                odswiezGre();
            }
            else {
                Komentarz.dodajKomentarz("Niepoprawne polecenie");
                odswiezGre();
            }
        }
    }

    public void wystartujGre() {
        siatka.setLayout(new GridLayout(20, 20));
        String symbol;
        for (int i = 0; i < 20; i++) {
            for (int n = 0; n < 20; n++) {
                if (swiat.plansza[i][n] == null) {
                    siatka.add(new JLabel("#"));
                }
                else {
                    symbol = String.valueOf(swiat.plansza[i][n].getSymbol());
                    siatka.add(new JLabel(symbol));
                }
            }
        }
        zapiszGre = new JButton("Zapisz gre");
        nowaTura = new JButton("Nowa tura");
        autor = "Patryk Sowinski-Toczek, s191711\n";
        tura = "Obecna tura: " + swiat.getTura() + "\n";
        zapiszGre.addActionListener(this);
        nowaTura.addActionListener(this);
        frame.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(siatka, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        komentarz = Komentarz.wyswietlKomentarze();
        komentarze = new JTextArea(autor + tura + komentarz, 20, 25);
        komentarze.setEditable(false);
        JScrollPane scroll = new JScrollPane(komentarze);
        frame.add(scroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        frame.add(nowaTura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(zapiszGre, gbc);

        SwingUtilities.updateComponentTreeUI(frame);
        frame.requestFocusInWindow();
    }

    public void odswiezGre() {
        siatka.removeAll();
        String symbol;
        for (int i = 0; i < 20; i++) {
            for (int n = 0; n < 20; n++) {
                if (swiat.plansza[i][n] == null) {
                    siatka.add(new JLabel("#"));
                }
                else {
                    symbol = String.valueOf(swiat.plansza[i][n].getSymbol());
                    siatka.add(new JLabel(symbol));
                }
            }
        }
        odswiezKomentarze();
    }

    private void odswiezKomentarze() {
        tura = "Obecna tura: " + swiat.getTura() + "\n";
        komentarz = Komentarz.wyswietlKomentarze();
        komentarze.setText(autor + tura + komentarz);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
