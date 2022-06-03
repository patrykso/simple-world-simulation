#include "Swiat.h"
#include "Organizm.h"
#include "Zwierze.h"
#include "Roslina.h"
#include "Komentarz.h"
#include "Czlowiek.h"
#include "Owca.h"
#include "Antylopa.h"
#include "BarszczSosnowskiego.h"
#include "Guarana.h"
#include "Lis.h"
#include "Mlecz.h"
#include "Trawa.h"
#include "WilczeJagody.h"
#include "Wilk.h"
#include "Zolw.h"
#include <vector>
#include <algorithm>
#include <conio.h>

#define ZEZWOL_NA_ROZMNAZANIE true
#define ZEZWOL_NA_ROZPRZESTRZENIANIE true
#define zapelnienieSwiata 0.2

using namespace std;

Swiat::Swiat() : Swiat(20, 20) {}

Swiat::Swiat(int x, int y) {
    this->x = x;
    this->y = y;
    tura = 0;
    liczbaOrganizmow = 0;
    czlowiekWykonalRuch = false;
    czlowiekWsk = nullptr;
    rozmnazanie = ZEZWOL_NA_ROZMNAZANIE;
    rozprzestrzenianie = ZEZWOL_NA_ROZPRZESTRZENIANIE;

    //tworzenie pustej planszy
    plansza = new Organizm ** [y];
    for(int i = 0; i < y; i++) plansza[i] = new Organizm * [x];
    for (int i = 0; i < y; i++) {
        for (int j = 0; j < x; j++) {
            plansza[i][j] = nullptr;
        }
    }
}

void Swiat::rysujPlansze() {
    for(int i = 0; i < y; i++) {
        for (int j = 0; j < x; j++) {
            if(plansza[i][j] == nullptr) cout << "#";
            else plansza[i][j]->rysowanie();
            if (j == (x - 1)) cout << endl;
        }
    }
}

void Swiat::rysujSwiat() {
    system("cls");
    cout << "Autor projektu: Patryk Sowinski-Toczek, s191711" << endl;
    cout << "Obecna tura: " << tura << endl;
    cout << "Strzalki - ruch gracza" << endl;
    cout << "P - specjalna umiejetnosc" << endl;
    rysujPlansze();
    Komentarz::wyswietlKomentarze();
}

Punkt Swiat::losujWolnePole() {
    for (int i = 0; i < y; i++) {
        for (int j = 0; j < x; i++) {
            if (plansza[i][j] == nullptr) {
                while (true) {
                    int pX = rand() % x;
                    int pY = rand() % y;
                    return Punkt(pX, pY);
                }
            }
        }
    }
    return Punkt(-1, -1);
}

void Swiat::generuj() {
    liczbaOrganizmow = x * y * zapelnienieSwiata;
    //stworz czlowieka na losowej pozycji
    Punkt pole = losujWolnePole();
    if (pole.getX() == -1 || pole.getY() == -1) return;
    else {
        Czlowiek *czlowiek = new Czlowiek(this, pole, this->tura);
        czlowiekWsk = czlowiek;
        dodajOrganizm(czlowiek);
    }
    //losuj typ organizmu i jego pozycje w swiecie zgodnie z liczba organizmow
    for (int i = 0; i < liczbaOrganizmow - 1; i++) {
        pole = losujWolnePole();
        if (pole.getX() == -1 || pole.getY() == -1) return;
        else {
            dodajOrganizm(Swiat::wylosujOrganizm(pole));
        }
    }
}

int Swiat::getX() {
    return this->x;
}

int Swiat::getY() {
    return this->y;
}

Organizm* Swiat::wylosujOrganizm(Punkt pole) {
    int los = rand() % 9;
    if(los == 0) return new Wilk(this, pole, this->tura);
    else if(los == 1) return new Owca(this, pole, this->tura);
    else if(los == 2) return new Lis(this, pole, this->tura);
    else if(los == 3) return new Zolw(this, pole, this->tura);
    else if(los == 4) return new Antylopa(this, pole, this->tura);
    else if(los == 5) return new Trawa(this, pole, this->tura);
    else if(los == 6) return new Mlecz(this, pole, this->tura);
    else if(los == 7) return new Guarana(this, pole, this->tura);
    else if(los == 8) return new WilczeJagody(this, pole, this->tura);
    else if(los == 9) return new BarszczSosnowskiego(this, pole, this->tura);
}

void Swiat::dodajOrganizm(Organizm *organizm) {
    listaOrganizmow.push_back(organizm);
    plansza[organizm->getPole().getY()][organizm->getPole().getX()] = organizm;
}

void Swiat::sortujOrganizmy() {
    //wystarczy posortowac po inicjatywie, bo najmlodsze organizmy
    //sa dodawane na koniec vectora
    sort(listaOrganizmow.begin(), listaOrganizmow.end(), []
            (Organizm *first, Organizm *second) {
       return first->getInicjatywa() < second->getInicjatywa();
    });
}

bool Swiat::koniecGry() {
    if(czlowiekWsk->czyZyje()) return false;
    else return true;
}

void Swiat::wykonajTure() {
    if(czlowiekWykonalRuch) return;
    Komentarz::czyscKomentarze();
    sortujOrganizmy();
    czlowiekWsk->zmniejszCooldownUmiejetnosci();
    czlowiekWsk->zmniejszSile();
    tura++;
    setCzlowiekWykonalRuch(false);
    for (int i = 0; i < listaOrganizmow.size(); i++) {
        if(listaOrganizmow.at(i) == nullptr) continue;
        if (listaOrganizmow.at(i)->czyZyje()) {
            if(listaOrganizmow.at(i) == czlowiekWsk) {
                if (!getCzlowiekWykonalRuch()) {
                    przyjmijPolecenie();
                    setCzlowiekWykonalRuch(false);
                }
                continue;
            }
            else listaOrganizmow.at(i)->akcja(listaOrganizmow.at(i)->getPole());
        }
    }
}

void Swiat::przyjmijPolecenie() {
    Czlowiek *czlowiek = czlowiekWsk;
    Punkt poleCzlowieka = czlowiekWsk->getPole();
    sortujOrganizmy();
    while (true) {
        int znak;
        znak = _getch();
        if (znak == IF_RUCH) {
            znak = _getch();
            if(znak == ruchWPrawo) {
                if(czlowiek->getPole().getX() + 1 < getX()) {
                    plansza[poleCzlowieka.getY()][poleCzlowieka.getX()] = nullptr;
                    poleCzlowieka = Punkt(poleCzlowieka.getX() + 1, poleCzlowieka.getY());
                    czlowiek->setPole(poleCzlowieka);
                    czlowiek->akcja(poleCzlowieka);
                    break;
                }
                else {
                    cout << "Nie mozesz sie tu ruszyc" << endl;
                    continue;
                }
            }
            else if(znak == ruchWLewo) {
                if(czlowiek->getPole().getX() - 1 >= 0) {
                    plansza[poleCzlowieka.getY()][poleCzlowieka.getX()] = nullptr;
                    poleCzlowieka = Punkt(poleCzlowieka.getX() - 1, poleCzlowieka.getY());
                    czlowiek->setPole(poleCzlowieka);
                    czlowiek->akcja(poleCzlowieka);
                    break;
                }
                else {
                    cout << "Nie mozesz sie tu ruszyc" << endl;
                    continue;
                }
            }
            else if(znak == ruchWGore) {
                if(czlowiek->getPole().getY() - 1 >= 0) {
                    plansza[poleCzlowieka.getY()][poleCzlowieka.getX()] = nullptr;
                    poleCzlowieka = Punkt(poleCzlowieka.getX(), poleCzlowieka.getY() - 1);
                    czlowiek->setPole(poleCzlowieka);
                    czlowiek->akcja(poleCzlowieka);
                    break;
                }
                else {
                    cout << "Nie mozesz sie tu ruszyc" << endl;
                    continue;
                }
            }
            else if(znak == ruchWDol) {
                if(czlowiek->getPole().getY() + 1 < getY()) {
                    plansza[poleCzlowieka.getY()][poleCzlowieka.getX()] = nullptr;
                    poleCzlowieka = Punkt(poleCzlowieka.getX(), poleCzlowieka.getY() + 1);
                    czlowiek->setPole(poleCzlowieka);
                    czlowiek->akcja(poleCzlowieka);
                    break;
                }
                else {
                    cout << "Nie mozesz sie tu ruszyc" << endl;
                    continue;
                }
            }
        }
        else if(znak == specjalnaUmiejetnosc) {
            if (czlowiekWsk->getCooldownUmiejetnosci() == 0) {
                cout << "Aktywowales specjalna umiejetnosc czlowieka" << endl;
                czlowiekWsk->aktywujSpecjalnaUmiejetnosc();
            }
            else cout << "Nie mozesz jeszcze aktywowac specjalnej umiejetnosci" << endl;
            continue;
        }
        else {
            cout << "Niepoprawne polecenie" << endl;
            continue;
        }
    }
}

Swiat::~Swiat() {
    for(int i = 0; i < y; i++) delete [] plansza[i];
    delete [] plansza;
}

bool Swiat::czyPoleZajete(Punkt pole) {
    if (plansza[pole.getY()][pole.getX()] == nullptr) return false;
    else return true;
}

Organizm *Swiat::getPole(Punkt pole) {
    return plansza[pole.getY()][pole.getX()];
}

void Swiat::przemiescOrganizm(Organizm *organizm, Punkt nowePole) {
    plansza[organizm->getPole().getY()][organizm->getPole().getX()] = nullptr;
    plansza[nowePole.getY()][nowePole.getX()] = organizm;
    organizm->setPole(nowePole);
}

void Swiat::zabijOrganizm(Organizm *organizm) {
    organizm->setUmarl();
    Punkt pole = organizm->getPole();
    plansza[pole.getY()][pole.getX()] = nullptr;
}

bool Swiat::silniejszyOrganizm(Punkt pole, int inicjatywa) {
    if (plansza[pole.getY()][pole.getX()] == nullptr) return false;
    else if (plansza[pole.getY()][pole.getX()]->getInicjatywa() > inicjatywa) return true;
    return false;
}

void Swiat::setCzlowiekWykonalRuch(bool state) {
    this->czlowiekWykonalRuch = state;
}

bool Swiat::getCzlowiekWykonalRuch() {
    return czlowiekWykonalRuch;
}

int Swiat::getTura() {
    return this->tura;
}

bool Swiat::getZezwolNaRozmnazanie() {
    return rozmnazanie;
}

bool Swiat::getZezwolNaRozprzestrzenianie() {
    return rozprzestrzenianie;
}
