#include "Trawa.h"
#include "Komentarz.h"

Trawa::Trawa(Swiat *swiat, Punkt pole, int turaPowstania)
    : Roslina(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_TRAWY;
    this->sila = SILA_TRAWY;
    this->inicjatywa = INICJATYWA_TRAWY;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

string Trawa::nazwaOrganizmu() {
    return "Trawa";
}
