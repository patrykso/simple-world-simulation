#include "Zolw.h"
#include "Komentarz.h"

Zolw::Zolw(Swiat *swiat, Punkt pole, int turaPowstania)
    : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol) {
    this->symbol = SYMBOL_ZOLWIA;
    this->sila = SILA_ZOLWIA;
    this->inicjatywa = INICJATYWA_ZOLWIA;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

void Zolw::akcja(Punkt pole) {
    Punkt nowePole = Punkt(-2, -2);
    if (czyZolwMozeSieRuszyc()) { //specjalna akcja zolwia
        while(true) {
            nowePole = losujPunktWZasiegu(pole, ZASIEG_RUCHU_ZOLWIA);
            if (nowePole.getY() >= swiat->getY() || nowePole.getY() < 0) continue;
            else if (nowePole.getX() >= swiat->getX() || nowePole.getX() < 0) continue;
            else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
            else break;
        }
        if (swiat->czyPoleZajete(nowePole)) {
            swiat->getPole(nowePole)->kolizja(this);
        }
        else {
            swiat->przemiescOrganizm(this, nowePole);
        }
    }
}

void Zolw::kolizja(Organizm *innyOrganizm) {
    if(innyOrganizm->nazwaOrganizmu() == this->nazwaOrganizmu() && swiat->getTura() + 1 > this->turaPowstania && swiat->getZezwolNaRozmnazanie()) {
        Punkt poleNowegoOrganizmu = Punkt(-2, -2);
        while(true) {
            poleNowegoOrganizmu = losujPunktWZasiegu(this->getPole(), 1);
            if (poleNowegoOrganizmu.getY() >= swiat->getY() || poleNowegoOrganizmu.getY() < 0) continue;
            else if (poleNowegoOrganizmu.getX() >= swiat->getX() || poleNowegoOrganizmu.getX() < 0) continue;
            else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
            else break;
        }
        swiat->dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " rozmnaza sie");
    }
    if(this->sila >= innyOrganizm->getSila()) {
        swiat->zabijOrganizm(innyOrganizm);
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i przegrywa");
    }
    else {
        if (innyOrganizm->getSila() < 5); //specjalna kolizja zolwia
        else {
            swiat->zabijOrganizm(this);
            swiat->przemiescOrganizm(innyOrganizm, this->getPole());
            Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i wygrywa");
        }
    }
}

bool Zolw::czyZolwMozeSieRuszyc() {
    int los = rand() % 100;
    if (los <= 25) return true;
    else return false;
}

string Zolw::nazwaOrganizmu() {
    return "Zolw";
}
