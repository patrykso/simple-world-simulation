#pragma once
#include "Zwierze.h"

#define SYMBOL_CZLOWIEKA 'C'
#define SILA_CZLOWIEKA 5
#define INICJATYWA_CZLOWIEKA 4

class Czlowiek : public Zwierze {
private:
    int cooldownUmiejetnosci;
protected:
public:
    Czlowiek(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
    virtual void akcja(Punkt pole) override;
    void aktywujSpecjalnaUmiejetnosc();
    int getCooldownUmiejetnosci();
    void zmniejszCooldownUmiejetnosci();
    void zmniejszSile();
};
