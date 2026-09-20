package org.example.format;

import org.example.words.NumberWords;

/**
 * Renders a minute count as it's spoken in "past"/"to" phrases, where 15
 * minutes is always said as "quarter" rather than "fifteen". Both
 * {@link PastTheHourRule} and {@link ToTheHourRule} need this same
 * substitution, so it lives here once instead of being duplicated.
 */
final class MinuteWord {

    private static final int QUARTER = 15;

    private MinuteWord() {
    }

    static String spoken(int minutes) {
        return minutes == QUARTER ? "quarter" : NumberWords.of(minutes);
    }
}