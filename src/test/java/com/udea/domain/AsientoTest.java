package com.udea.domain;

import static com.udea.domain.AsientoTestSamples.*;
import static com.udea.domain.ReservaTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AsientoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Asiento.class);
        Asiento asiento1 = getAsientoSample1();
        Asiento asiento2 = new Asiento();
        assertThat(asiento1).isNotEqualTo(asiento2);

        asiento2.setId(asiento1.getId());
        assertThat(asiento1).isEqualTo(asiento2);

        asiento2 = getAsientoSample2();
        assertThat(asiento1).isNotEqualTo(asiento2);
    }

    @Test
    void reservasTest() {
        Asiento asiento = getAsientoRandomSampleGenerator();
        Reserva reservaBack = getReservaRandomSampleGenerator();

        asiento.addReservas(reservaBack);
        assertThat(asiento.getReservases()).containsOnly(reservaBack);
        assertThat(reservaBack.getAsiento()).isEqualTo(asiento);

        asiento.removeReservas(reservaBack);
        assertThat(asiento.getReservases()).doesNotContain(reservaBack);
        assertThat(reservaBack.getAsiento()).isNull();

        asiento.reservases(new HashSet<>(Set.of(reservaBack)));
        assertThat(asiento.getReservases()).containsOnly(reservaBack);
        assertThat(reservaBack.getAsiento()).isEqualTo(asiento);

        asiento.setReservases(new HashSet<>());
        assertThat(asiento.getReservases()).doesNotContain(reservaBack);
        assertThat(reservaBack.getAsiento()).isNull();
    }
}
