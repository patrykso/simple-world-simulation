#include "Guarana.h"
#include "Komentarz.h"

Guarana::Guarana(Swiat *swiat, Punkt pole, int turaPowstania)
    : Roslina(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_GUARANY;
    this->sila = SILA_GUARANY;
    this->inicjatywa = INICJATYWA_GUARANY;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

void Guarana::kolizja(Organizm *innyOrganizm) {
    swiat->zabijOrganizm(this);
    if(this->sila >= innyOrganizm->getSila()) {
        swiat->zabijOrganizm(innyOrganizm);
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " zjada " + this->nazwaOrganizmu() + " i ginie");
    }
    else {
        swiat->przemiescOrganizm(innyOrganizm, this->getPole());
        innyOrganizm->setSila(innyOrganizm->getSila() + 3); //specjalna kolizja guarany
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " zjada " + this->nazwaOrganizmu() + " i otrzymuje +3 sily");
    }
}

string Guarana::nazwaOrganizmu() {
    return "Guarana";
}
