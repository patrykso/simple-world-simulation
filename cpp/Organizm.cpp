#include "Organizm.h"

Punkt Organizm::getPole() {
    return Punkt(pole.getX(), pole.getY());
}

void Organizm::rysowanie() {
    cout << symbol;
}

Organizm::Organizm(Swiat *swiat, Punkt pole, int turaPowstania, int sila,
                   int inicjatywa, char symbol) {
    this->swiat = swiat;
    this->pole = pole;
    this->turaPowstania = turaPowstania;
    this->sila = sila;
    this->symbol = symbol;
    this->inicjatywa = inicjatywa;
    this->umarl = false;
}

int Organizm::getInicjatywa() {
    return this->inicjatywa;
}

void Organizm::setPole(Punkt nowePole) {
    this->pole = nowePole;
}

int Organizm::getSila() {
    return this->sila;
}

void Organizm::setUmarl() {
    this->umarl = true;
}

bool Organizm::czyZyje() {
    if(umarl) return false;
    else return true;
}

void Organizm::kolizja(Organizm *innyOrganizm) {

}

void Organizm::akcja(Punkt pole) {

}

void Organizm::setSila(int sila) {
    this->sila = sila;
}

string Organizm::nazwaOrganizmu() {

}

Punkt Organizm::losujPunktWZasiegu(Punkt pole, int zasieg) {
    int los = rand() % 100;
    if (los <= 25) {
        return Punkt(pole.getX() + zasieg, pole.getY());
    }
    else if (los <= 50) {
        return Punkt(pole.getX(), pole.getY() + zasieg);
    }
    else if (los <= 75) {
        return Punkt(pole.getX() - zasieg, pole.getY());
    }
    else if (los <= 100) {
        return Punkt(pole.getX(), pole.getY() - zasieg);
    }
    else return Punkt(-4, -4);
}
