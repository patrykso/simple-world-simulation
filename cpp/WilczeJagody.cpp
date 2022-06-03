#include "WilczeJagody.h"
#include "Komentarz.h"

WilczeJagody::WilczeJagody(Swiat *swiat, Punkt pole, int turaPowstania)
    : Roslina(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_WILCZYCH_JAGOD;
    this->sila = SILA_WILCZYCH_JAGOD;
    this->inicjatywa = INICJATYWA_WILCZYCH_JAGOD;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

string WilczeJagody::nazwaOrganizmu() {
    return "Wilcze Jagody";
}
