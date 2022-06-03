#include "Komentarz.h"

string Komentarz::komentarz = "";

void Komentarz::wyswietlKomentarze()
{
    if (komentarz == "") return;
    else cout << komentarz;
}

void Komentarz::dodajKomentarz(string nowyKomentarz) {
    komentarz += nowyKomentarz + "\n";
}

void Komentarz::czyscKomentarze()
{
    komentarz = "";
}
