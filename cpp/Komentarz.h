#pragma once
#include <iostream>

using namespace std;

class Komentarz {
private:
    static string komentarz;
protected:
public:
    static void wyswietlKomentarze();
    static void dodajKomentarz(string komentarz);
    static void czyscKomentarze();
};
