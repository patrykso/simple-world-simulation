#include "Czlowiek.h"

Czlowiek::Czlowiek(Swiat *swiat, Punkt pole, int turaPowstania)
        : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_CZLOWIEKA;
    this->sila = SILA_CZLOWIEKA;
    this->inicjatywa = INICJATYWA_CZLOWIEKA;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
    this->cooldownUmiejetnosci = 0;
}

string Czlowiek::nazwaOrganizmu() {
    return "Czlowiek";
}

void Czlowiek::akcja(Punkt pole) {
    if (swiat->czyPoleZajete(pole)) {
        swiat->getPole(pole)->kolizja(this);

    }
    else {
        swiat->przemiescOrganizm(this, pole);
    }
}

void Czlowiek::aktywujSpecjalnaUmiejetnosc() {
    this->setSila(10);
    this->cooldownUmiejetnosci = 10;
}

int Czlowiek::getCooldownUmiejetnosci() {
    return this->cooldownUmiejetnosci;
}

void Czlowiek::zmniejszCooldownUmiejetnosci() {
    if (cooldownUmiejetnosci > 0) this->cooldownUmiejetnosci--;
}

void Czlowiek::zmniejszSile() {
    if (sila > 5) this->sila--;
}
