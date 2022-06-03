#include "Mlecz.h"
#include "Komentarz.h"

Mlecz::Mlecz(Swiat *swiat, Punkt pole, int turaPowstania)
    : Roslina(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_MLECZA;
    this->sila = SILA_MLECZA;
    this->inicjatywa = INICJATYWA_MLECZA;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

void Mlecz::akcja(Punkt pole) {
    for(int i = 0; i < 3; i++) {
        int los = rand() % 100;
        if (los <= 25 && swiat->getZezwolNaRozprzestrzenianie()) {
            Punkt poleNowegoOrganizmu = Punkt(-2, -2);
            while(true) {
                poleNowegoOrganizmu = losujPunktWZasiegu(this->getPole(), 1);
                if (poleNowegoOrganizmu.getY() >= swiat->getY() || poleNowegoOrganizmu.getY() < 0) continue;
                else if (poleNowegoOrganizmu.getX() >= swiat->getX() || poleNowegoOrganizmu.getX() < 0) continue;
                else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
                else break;
            }
            swiat->dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
            Komentarz::dodajKomentarz(this->nazwaOrganizmu() + " rozprzestrzenia sie");
        }
    }
}

string Mlecz::nazwaOrganizmu() {
    return "Mlecz";
}
