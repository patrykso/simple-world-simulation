#include "Roslina.h"
#include "Komentarz.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "WilczeJagody.h"
#include "BarszczSosnowskiego.h"

Roslina::Roslina(Swiat *swiat, Punkt pole, int turaPowstania,
                 int sila, int inicjatywa, char symbol)
        : Organizm(swiat, pole, turaPowstania, sila, inicjatywa, symbol) {

}

void Roslina::akcja(Punkt pole) {
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

void Roslina::kolizja(Organizm *innyOrganizm) {
    swiat->zabijOrganizm(this);
    if(this->sila >= innyOrganizm->getSila()) {
        swiat->zabijOrganizm(innyOrganizm);
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " zjada " + this->nazwaOrganizmu() + " i ginie");
    }
    else {
        swiat->przemiescOrganizm(innyOrganizm, this->getPole());
        Komentarz::dodajKomentarz(innyOrganizm->nazwaOrganizmu() + " zjada " + this->nazwaOrganizmu());
    }
}

Organizm *Roslina::getTypRozmnozonegoOrganizmu(Punkt pole) {
    if(this->nazwaOrganizmu() == "Trawa") return new Trawa(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Mlecz") return new Mlecz(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Guarana") return new Guarana(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Wilcze Jagody") return new WilczeJagody(swiat, pole, swiat->getTura());
    else if(this->nazwaOrganizmu() == "Barszcz Sosnowskiego") return new BarszczSosnowskiego(swiat, pole, swiat->getTura());
}

string Roslina::nazwaOrganizmu() {

}