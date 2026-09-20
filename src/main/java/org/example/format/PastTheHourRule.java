package org.example.format;

import org.example.model.ClockTime;
import org.example.words.NumberWords;

/**
 * Phrases minutes between the hour and the half hour, on a five-minute
 * mark, as "M past H", e.g. 05:20 -&gt; "twenty past five".
 */
final class PastTheHourRule implements TimeFormattingRule {

    @Override
    public boolean appliesTo(ClockTime time) {
        return time.isBeforeHalfPast() && time.isOnAFiveMinuteMark();
    }

    @Override
    public String format(ClockTime time) {
        return MinuteWord.spoken(time.minute()) + " past " + NumberWords.of(time.hourOfTwelveClock());
    }
}
