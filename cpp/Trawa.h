#pragma once
#include "Roslina.h"

#define SYMBOL_TRAWY 'T'
#define SILA_TRAWY 0
#define INICJATYWA_TRAWY 0

class Trawa : public Roslina {
private:
protected:
public:
    Trawa(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
};