package com.biolivre.teste.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.biolivre.teste.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InfoAdicionalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfoAdicional.class);
        InfoAdicional infoAdicional1 = new InfoAdicional();
        infoAdicional1.setId(1L);
        InfoAdicional infoAdicional2 = new InfoAdicional();
        infoAdicional2.setId(infoAdicional1.getId());
        assertThat(infoAdicional1).isEqualTo(infoAdicional2);
        infoAdicional2.setId(2L);
        assertThat(infoAdicional1).isNotEqualTo(infoAdicional2);
        infoAdicional1.setId(null);
        assertThat(infoAdicional1).isNotEqualTo(infoAdicional2);
    }
}
