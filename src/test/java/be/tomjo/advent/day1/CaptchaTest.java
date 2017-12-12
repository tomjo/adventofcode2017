package be.tomjo.advent.day1;

import be.tomjo.advent.day1.Captcha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class CaptchaTest {

    private Captcha captcha;

    @BeforeEach
    void setUp() {
        captcha = new Captcha();
    }

    @Nested
    class SolveCaptchaWithNextDigitMatching {

        @Test
        void noMatchingDigits_returnsZero() {
            assertThat(captcha.solveCaptchaWithNextDigitMatching(asList(1, 2, 3, 4))).isEqualTo(0);
        }

        @Test
        void allDigitsMatch_returnsLengthOfInput() {
            assertThat(captcha.solveCaptchaWithNextDigitMatching(asList(1, 1, 1, 1))).isEqualTo(4);
        }

        @Test
        void examples() {
            assertThat(captcha.solveCaptchaWithNextDigitMatching(asList(1,1,2,2))).isEqualTo(3);
            assertThat(captcha.solveCaptchaWithNextDigitMatching(asList(9,1,2,1,2,1,2,9))).isEqualTo(9);
        }
    }


    @Nested
    class SolveCaptchaWithHalfWayAroundDigitMatching {

        @Test
        void moreExamples() {
            assertThat(captcha.solveCaptchaWithHalfWayAroundDigitMatching(asList(1, 2, 1, 2))).isEqualTo(6);
            assertThat(captcha.solveCaptchaWithHalfWayAroundDigitMatching(asList(1, 2, 2, 1))).isEqualTo(0);
            assertThat(captcha.solveCaptchaWithHalfWayAroundDigitMatching(asList(1, 2, 3, 4, 2, 5))).isEqualTo(4);
            assertThat(captcha.solveCaptchaWithHalfWayAroundDigitMatching(asList(1, 2, 3, 1, 2, 3))).isEqualTo(12);
            assertThat(captcha.solveCaptchaWithHalfWayAroundDigitMatching(asList(1, 2, 1, 3, 1, 4, 1, 5))).isEqualTo(4);
        }
    }

}