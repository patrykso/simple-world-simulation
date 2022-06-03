#include "Zwierze.h"
#include "Komentarz.h"
#include "Antylopa.h"
#include "Zolw.h"
#include "Lis.h"
#include "Owca.h"
#include "Wilk.h"

Zwierze::Zwierze(Swiat *swiat, Punkt pole, int turaPowstania,
                 int sila, int inicjatywa, char symbol)
    : Organizm(swiat, pole, turaPowstania, sila, inicjatywa, symbol) {

}

void Zwierze::akcja(Punkt pole) {
    Punkt nowePole = Punkt(-2, -2);
    while(true) {
        nowePole = losujPunktWZasiegu(pole, 1);
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

Organizm *Zwierze::getTypRozmnozonegoOrganizmu(Punkt pole) {
    if(this->nazwaOrganizmu() == "Wilk") return new Wilk(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Owca") return new Owca(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Lis") return new Lis(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Zolw") return new Zolw(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Antylopa") return new Antylopa(swiat, pole, swiat->getTura());
}

void Zwierze::kolizja(Organizm *innyOrganizm) {
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
    else if(this->sila >= innyOrganizm->getSila()) {
        swiat->zabijOrganizm(innyOrganizm);
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i przegrywa");
    }
    else {
        swiat->zabijOrganizm(this);
        swiat->przemiescOrganizm(innyOrganizm, this->getPole());
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " atakuje " + this->nazwaOrganizmu() + " i wygrywa");
    }
}

string Zwierze::nazwaOrganizmu() {

}
