package org.example.format;

import org.example.model.ClockTime;
import org.example.words.NumberWords;

/**
 * Phrases the two exact minute markers that have their own fixed wording:
 * on the hour ("H o'clock") and the half hour ("half past H"). Grouped into
 * one rule because both are "this minute value always says the same
 * fixed word, regardless of which hour it is" - just a different fixed
 * word for 0 vs. 30.
 *
 * <p>Only reached once {@link SpecialNameRule} has declined, since midnight
 * and noon are themselves on-the-hour times with their own special names.
 */
final class ExactMinuteRule implements TimeFormattingRule {

    @Override
    public boolean appliesTo(ClockTime time) {
        return time.isOnTheHour() || time.isHalfPast();
    }

    @Override
    public String format(ClockTime time) {
        String hourWord = NumberWords.of(time.hourOfTwelveClock());
        return time.isOnTheHour() ? hourWord + " o'clock" : "half past " + hourWord;
    }
}
