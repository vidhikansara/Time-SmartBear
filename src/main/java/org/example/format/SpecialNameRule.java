package org.example.format;


import org.example.model.ClockTime;

/**
 * Phrases the day's two special moments: 00:00 as "midnight" and 12:00 as
 * "noon". Grouped into one rule because they're really the same idea -
 * "this exact time has its own name, not a generic phrase" - just for two
 * different times.
 */
final class SpecialNameRule implements TimeFormattingRule {

    @Override
    public boolean appliesTo(ClockTime time) {
        return time.isMidnight() || time.isNoon();
    }

    @Override
    public String format(ClockTime time) {
        return time.isMidnight() ? "midnight" : "noon";
    }
}

