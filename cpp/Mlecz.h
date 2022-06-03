#pragma once
#include "Roslina.h"

#define SYMBOL_MLECZA 'M'
#define SILA_MLECZA 0
#define INICJATYWA_MLECZA 0

class Mlecz : public Roslina {
private:
protected:
public:
    Mlecz(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual void akcja(Punkt pole) override;
    virtual string nazwaOrganizmu() override;
};