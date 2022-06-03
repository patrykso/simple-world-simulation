#include "Wilk.h"
#include "Komentarz.h"

Wilk::Wilk(Swiat *swiat, Punkt pole, int turaPowstania)
    : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_WILKA;
    this->turaPowstania = turaPowstania;
    this->sila = SILA_WILKA;
    this->inicjatywa = INICJATYWA_WILKA;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
}

string Wilk::nazwaOrganizmu() {
    return "Wilk";
}
