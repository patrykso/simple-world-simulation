#include "Owca.h"
#include "Komentarz.h"

Owca::Owca(Swiat *swiat, Punkt pole, int turaPowstania)
    : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_OWCY;
    this->sila = SILA_OWCY;
    this->inicjatywa = INICJATYWA_OWCY;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

string Owca::nazwaOrganizmu() {
    return "Owca";
}
