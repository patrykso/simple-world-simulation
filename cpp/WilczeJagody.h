#pragma once
#include "Roslina.h"

#define SYMBOL_WILCZYCH_JAGOD 'J'
#define SILA_WILCZYCH_JAGOD 99
#define INICJATYWA_WILCZYCH_JAGOD 0

class WilczeJagody : public Roslina {
private:
protected:
public:
    WilczeJagody(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
};