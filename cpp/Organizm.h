#pragma once
#include "Swiat.h"

class Swiat;
class Punkt;

class Organizm {
private:
protected:
    int sila, inicjatywa, turaPowstania;
    char symbol;
    Swiat *swiat;
    Punkt pole;
    bool umarl;
    Punkt losujPunktWZasiegu(Punkt pole, int zasieg);
public:
    Organizm(Swiat *swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol);
    Punkt getPole();
    virtual void rysowanie();
    virtual void akcja(Punkt pole);
    virtual void kolizja(Organizm *innyOrganizm);
    virtual string nazwaOrganizmu();
    int getInicjatywa();
    void setPole(Punkt nowePole);
    void setUmarl();
    bool czyZyje();
    int getSila();
    void setSila(int sila);
};
