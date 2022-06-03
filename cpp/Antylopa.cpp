#include "Antylopa.h"
#include "Komentarz.h"

Antylopa::Antylopa(Swiat *swiat, Punkt pole, int turaPowstania)
    : Zwierze(swiat, pole, turaPowstania, sila, inicjatywa, symbol) {
    this->symbol = SYMBOL_ANTYLOPY;
    this->sila = SILA_ANTYLOPY;
    this->inicjatywa = INICJATYWA_ANTYLOPY;
    this->pole = pole;
    this->swiat = swiat;
    this->umarl = false;
    this->turaPowstania = turaPowstania;
}

void Antylopa::akcja(Punkt pole) {
    Punkt nowePole = Punkt(-2, -2);
    while(true) {
        nowePole = losujPunktWZasiegu(pole, ZASIEG_RUCHU_ANTYLOPY); //specjalna akcja antylopy
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

void Antylopa::kolizja(Organizm *innyOrganizm) {
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
    else if(czyAntylopaMozeUciec()) { //specjalna kolizja antylopy
        Punkt nowePole = Punkt(-2, -2);
        while(true) {
            nowePole = losujPunktWZasiegu(pole, ZASIEG_RUCHU_ANTYLOPY);
            if (nowePole.getY() >= swiat->getY() || nowePole.getY() < 0) continue;
            else if (nowePole.getX() >= swiat->getX() || nowePole.getX() < 0) continue;
            else if (swiat->czyPoleZajete(nowePole)) continue;
            else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
            else break;
        }
        swiat->przemiescOrganizm(this, nowePole);
    }
    else {
        if(this->sila >= innyOrganizm->getSila()) {
            swiat->zabijOrganizm(innyOrganizm);
            Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i przegrywa");
        }
        else {
            swiat->zabijOrganizm(this);
            swiat->przemiescOrganizm(innyOrganizm, this->getPole());
            Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i wygrywa");
        }
    }
}

bool Antylopa::czyAntylopaMozeUciec() {
    int los = rand() % 100;
    if (los <= 50) return true;
    else return false;
}

string Antylopa::nazwaOrganizmu() {
    return "Antylopa";
}
