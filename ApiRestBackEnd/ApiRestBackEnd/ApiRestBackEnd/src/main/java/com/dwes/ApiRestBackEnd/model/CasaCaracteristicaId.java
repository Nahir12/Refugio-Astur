package com.dwes.ApiRestBackEnd.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CasaCaracteristicaId {
        private Long casaId;
        private Long caracteristicaId;
}
