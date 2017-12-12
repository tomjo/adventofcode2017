package be.tomjo.advent.day4;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day4.PassPhraseValidator.isValidPassPhrase1;
import static be.tomjo.advent.day4.PassPhraseValidator.isValidPassPhrase2;
import static org.assertj.core.api.Assertions.assertThat;

class PassPhraseValidatorTest {

    @Test
    void examples1() {
        assertThat(isValidPassPhrase1("aa bb cc dd ee")).isTrue();
        assertThat(isValidPassPhrase1("aa bb cc dd aa")).isFalse();
        assertThat(isValidPassPhrase1("aa bb cc dd aaa")).isTrue();
    }

    @Test
    void examples2() {
        assertThat(isValidPassPhrase2("abcde fghij")).isTrue();
        assertThat(isValidPassPhrase2("abcde xyz ecdab")).isFalse();
        assertThat(isValidPassPhrase2("a ab abc abd abf abj")).isTrue();
        assertThat(isValidPassPhrase2("iiii oiii ooii oooi oooo")).isTrue();
        assertThat(isValidPassPhrase2("oiii ioii iioi iiio")).isFalse();
    }
}