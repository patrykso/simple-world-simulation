#pragma once
#include "Roslina.h"

#define SYMBOL_BARSZCZU_SOSNOWSKIEG 'B'
#define SILA_BARSZCZU_SOSNOWSKIEGO 10
#define INICJATYWA_BARSZCZU_SOSNOWSKIEGO 0

class BarszczSosnowskiego : public Roslina {
private:
protected:
public:
    BarszczSosnowskiego(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual void akcja(Punkt pole) override;
    virtual void kolizja(Organizm *innyOrganizm) override;
    virtual string nazwaOrganizmu() override;
};