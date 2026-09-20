package org.example.format;

import org.example.model.ClockTime;
import org.example.words.NumberWords;

/**
 * Catch-all for minutes that don't land on a five-minute mark, e.g. 06:32,
 * which has no colloquial phrasing and is just read out as "six thirty-two".
 *
 * <p>Always applies, so it's registered last: every other rule gets first
 * refusal, and this one guarantees every valid time produces some output.
 */
final class ExactMinuteFallbackRule implements TimeFormattingRule {

    @Override
    public boolean appliesTo(ClockTime time) {
        return true;
    }

    @Override
    public String format(ClockTime time) {
        return NumberWords.of(time.hourOfTwelveClock()) + " " + NumberWords.of(time.minute());
    }
}
