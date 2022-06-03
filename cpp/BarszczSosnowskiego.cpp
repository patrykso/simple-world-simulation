#include "BarszczSosnowskiego.h"
#include "Komentarz.h"

BarszczSosnowskiego::BarszczSosnowskiego(Swiat *swiat, Punkt pole, int turaPowstania)
    : Roslina(swiat, pole, turaPowstania, sila, inicjatywa, symbol){
    this->symbol = SYMBOL_BARSZCZU_SOSNOWSKIEG;
    this->sila = SILA_BARSZCZU_SOSNOWSKIEGO;
    this->inicjatywa = INICJATYWA_BARSZCZU_SOSNOWSKIEGO;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

//skoro zwierzeta moga poruszac sie tylko prawo lewo gora dol, zakladam, ze
//"sasiedztwo" to znajduje sie na kierunkach prawo lewo gora dol
//cyberowca nie zostala zaimplementowana, wiec barszcz zabija wszystkie zwierzeta
void BarszczSosnowskiego::akcja(Punkt pole) {
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
    if(pole.getX() - 1 > 0) {
        Punkt *tmpPole = new Punkt(pole.getX() - 1, pole.getY());
        if (swiat->czyPoleZajete(*tmpPole) && swiat->getPole(*tmpPole)->nazwaOrganizmu() != "Barszcz Sosnowskiego") {
            swiat->zabijOrganizm(swiat->getPole(*tmpPole));
            Komentarz::dodajKomentarz(swiat->getPole((*tmpPole))->nazwaOrganizmu() + "ginie od akcji barszczu");
        }

    }
    if(pole.getY() - 1 > 0) {
        Punkt *tmpPole = new Punkt(pole.getX(), pole.getY() - 1);
        if (swiat->czyPoleZajete(*tmpPole) && swiat->getPole(*tmpPole)->nazwaOrganizmu() != "Barszcz Sosnowskiego") {
            swiat->zabijOrganizm(swiat->getPole(*tmpPole));
            Komentarz::dodajKomentarz(swiat->getPole((*tmpPole))->nazwaOrganizmu() + "ginie od akcji barszczu");
        }
    }
    if(pole.getX() + 1 < swiat->getX()) {
        Punkt *tmpPole = new Punkt(pole.getX() + 1, pole.getY());
        if (swiat->czyPoleZajete(*tmpPole) && swiat->getPole(*tmpPole)->nazwaOrganizmu() != "Barszcz Sosnowskiego") {
            swiat->zabijOrganizm(swiat->getPole(*tmpPole));
            Komentarz::dodajKomentarz(swiat->getPole((*tmpPole))->nazwaOrganizmu() + "ginie od akcji barszczu");
        }
    }
    if(pole.getY() + 1 < swiat->getY()) {
        Punkt *tmpPole = new Punkt(pole.getX(), pole.getY() + 1);
        if (swiat->czyPoleZajete(*tmpPole) && swiat->getPole(*tmpPole)->nazwaOrganizmu() != "Barszcz Sosnowskiego") {
            swiat->zabijOrganizm(swiat->getPole(*tmpPole));
            Komentarz::dodajKomentarz(swiat->getPole((*tmpPole))->nazwaOrganizmu() + "ginie od akcji barszczu");
        }
    }
}

void BarszczSosnowskiego::kolizja(Organizm *innyOrganizm) {
    swiat->zabijOrganizm(this);
    swiat->zabijOrganizm(innyOrganizm); //specjalna kolizja barszczu
    Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " zjada " + this->nazwaOrganizmu() + " i ginie");
}

string BarszczSosnowskiego::nazwaOrganizmu() {
    return "Barszcz Sosnowskiego";
}
