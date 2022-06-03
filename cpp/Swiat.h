#pragma once
#include <vector>
#include <iostream>
#include "Punkt.h"
#define IF_RUCH 224
#define ruchWGore 72
#define ruchWDol 80
#define ruchWLewo 75
#define ruchWPrawo 77
#define specjalnaUmiejetnosc 112

using namespace std;

class Organizm;
class Czlowiek;

class Swiat {
private:
    int x, y; //wymiary planszy x, y
    int tura, liczbaOrganizmow;
    vector<Organizm*> listaOrganizmow;
    bool czlowiekWykonalRuch;
    bool rozmnazanie, rozprzestrzenianie;
    void sortujOrganizmy();
    Organizm *wylosujOrganizm(Punkt pole);
    Punkt losujWolnePole();
    Czlowiek *czlowiekWsk;
protected:
    Organizm ***plansza;
public:
    Swiat();
    Swiat(int x, int y);
    ~Swiat();
    void wykonajTure();
    void dodajOrganizm(Organizm *organizm);
    void przyjmijPolecenie();
    void rysujSwiat();
    void rysujPlansze();
    void generuj();
    bool koniecGry();
    bool silniejszyOrganizm(Punkt pole, int inicjatywa);
    void zabijOrganizm(Organizm *organizm);
    int getX();
    int getY();
    bool czyPoleZajete(Punkt pole);
    Organizm *getPole(Punkt pole);
    void przemiescOrganizm(Organizm *organizm, Punkt nowePole);
    void setCzlowiekWykonalRuch(bool state);
    bool getCzlowiekWykonalRuch();
    int getTura();
    bool getZezwolNaRozmnazanie();
    bool getZezwolNaRozprzestrzenianie();
};