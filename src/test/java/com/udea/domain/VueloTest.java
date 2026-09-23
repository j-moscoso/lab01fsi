package com.udea.domain;

import static com.udea.domain.ReservaTestSamples.*;
import static com.udea.domain.VueloTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.udea.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VueloTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vuelo.class);
        Vuelo vuelo1 = getVueloSample1();
        Vuelo vuelo2 = new Vuelo();
        assertThat(vuelo1).isNotEqualTo(vuelo2);

        vuelo2.setId(vuelo1.getId());
        assertThat(vuelo1).isEqualTo(vuelo2);

        vuelo2 = getVueloSample2();
        assertThat(vuelo1).isNotEqualTo(vuelo2);
    }

    @Test
    void reservasTest() {
        Vuelo vuelo = getVueloRandomSampleGenerator();
        Reserva reservaBack = getReservaRandomSampleGenerator();

        vuelo.addReservas(reservaBack);
        assertThat(vuelo.getReservases()).containsOnly(reservaBack);
        assertThat(reservaBack.getVuelo()).isEqualTo(vuelo);

        vuelo.removeReservas(reservaBack);
        assertThat(vuelo.getReservases()).doesNotContain(reservaBack);
        assertThat(reservaBack.getVuelo()).isNull();

        vuelo.reservases(new HashSet<>(Set.of(reservaBack)));
        assertThat(vuelo.getReservases()).containsOnly(reservaBack);
        assertThat(reservaBack.getVuelo()).isEqualTo(vuelo);

        vuelo.setReservases(new HashSet<>());
        assertThat(vuelo.getReservases()).doesNotContain(reservaBack);
        assertThat(reservaBack.getVuelo()).isNull();
    }
}
