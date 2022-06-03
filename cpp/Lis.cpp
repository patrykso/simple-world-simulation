#include "Lis.h"
#include "Komentarz.h"

Lis::Lis(Swiat *swiat, Punkt pole, int turaPowstania)
    : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_LISA;
    this->sila = SILA_LISA;
    this->inicjatywa = INICJATYWA_LISA;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

void Lis::akcja(Punkt pole) {
    Punkt nowePole = Punkt(-2, -2);
    while(true) {
        nowePole = losujPunktWZasiegu(pole, ZASIEG_RUCHU_LISA);
        if (nowePole.getY() >= swiat->getY() || nowePole.getY() < 0) continue;
        else if (nowePole.getX() >= swiat->getX() || nowePole.getX() < 0) continue;
        else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
        else if (!swiat->silniejszyOrganizm(nowePole, INICJATYWA_LISA)) break; //akcja specjalna lisa
        else break;
    }
    if (swiat->czyPoleZajete(nowePole)) {
        swiat->getPole(nowePole)->kolizja(this);
    }
    else {
        swiat->przemiescOrganizm(this, nowePole);
    }
}

string Lis::nazwaOrganizmu() {
    return "Lis";
}
