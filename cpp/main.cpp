#include <iostream>
#include <ctime>
#include "Swiat.h"

using namespace std;

int main() {
    srand(time(nullptr));;
    char znak;
    Swiat *swiat;
    while (true) {
        cout << "Autor projektu: Patryk Sowinski-Toczek, s191711" << endl;
        cout << "Menu:" << endl;
        cout << "N - rozpocznij nowa gre" << endl;
        cout << "Wpisz polecenie:" << endl;
        cin >> znak;

        if (znak == 'L' || znak == 'l') {
            continue;
        }
        else if (znak == 'N' || znak == 'n') {
            int n, m;
            cout << "Podaj wymiary planszy (x i y): " << endl;
            cin >> n >> m;
            swiat = new Swiat(n, m);
            swiat->generuj();
            break;
        }
        else {
            cout << "Wybierz ponownie" << endl;
        }
    }
    //gra sie konczy gdy czlowiek ginie
    while (!swiat->koniecGry()) {
        swiat->rysujSwiat();
        swiat->wykonajTure();
    }
    cout << "Czlowiek zginal. Zrestartuj program, aby rozpoczac nowa gre";

    delete swiat;
    return 0;
}
