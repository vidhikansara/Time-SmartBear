package org.example.format;

import org.example.model.ClockTime;
import org.example.words.NumberWords;

/**
 * Phrases minutes between the half hour and the next hour, on a five-minute
 * mark, by counting down to the next hour: "M to H", e.g. 07:35 -&gt;
 * "twenty-five to eight".
 */
final class ToTheHourRule implements TimeFormattingRule {

    @Override
    public boolean appliesTo(ClockTime time) {
        return time.isAfterHalfPast() && time.isOnAFiveMinuteMark();
    }

    @Override
    public String format(ClockTime time) {
        return MinuteWord.spoken(time.minutesToNextHour()) + " to " + NumberWords.of(time.nextHourOfTwelveClock());
    }
}
