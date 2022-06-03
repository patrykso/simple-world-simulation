#pragma once
#include "Zwierze.h"

#define SYMBOL_ZOLWIA 'Z'
#define SILA_ZOLWIA 2
#define INICJATYWA_ZOLWIA 1
#define ZASIEG_RUCHU_ZOLWIA 1

class Zolw : public Zwierze {
private:
    bool czyZolwMozeSieRuszyc();
protected:
public:
    Zolw(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual void akcja(Punkt pole) override;
    virtual void kolizja(Organizm *innyOrganizm) override;
    virtual string nazwaOrganizmu() override;
};