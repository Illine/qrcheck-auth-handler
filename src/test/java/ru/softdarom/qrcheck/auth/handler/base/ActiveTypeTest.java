package ru.softdarom.qrcheck.auth.handler.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.softdarom.qrcheck.auth.handler.model.base.ActiveType;
import ru.softdarom.qrcheck.auth.handler.test.tag.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UnitTest
@DisplayName("ActiveType Unit Test")
class ActiveTypeTest {

    //  -----------------------   successful tests   -------------------------

    @Test
    @DisplayName("activeOf(): returns 'ENABLED' when 'true'")
    void successfulActiveOfTrue() {
        assertEquals(ActiveType.ENABLED, ActiveType.activeOf(true));
    }

    @Test
    @DisplayName("activeOf(): returns 'DISABLED' when 'false'")
    void successfulActiveOfFalse() {
        assertEquals(ActiveType.DISABLED, ActiveType.activeOf(false));
    }

    @Test
    @DisplayName("toString(): returns a valid string when 'ENABLED'")
    void successfulToStringEnabled() {
        var expected = String.valueOf(true);
        assertEquals(expected, ActiveType.ENABLED.toString());
    }

    @Test
    @DisplayName("toString(): returns a valid string when 'DISABLED'")
    void successfulToStringDeleted() {
        var expected = String.valueOf(false);
        assertEquals(expected, ActiveType.DISABLED.toString());
    }

}