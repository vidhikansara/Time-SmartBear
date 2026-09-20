package org.example.model;

import java.util.Objects;

/**
 * An immutable, validated 24-hour clock time.
 *
 * <p>This is a pure value object: it knows nothing about how to render itself
 * as English words. It only exposes the facts about the time (is it on the
 * hour? how many minutes to the next hour?) that a formatter needs in order
 * to decide how to phrase it. Keeping that knowledge here, rather than
 * scattered across the formatting code, means every consumer works with the
 * same validated, normalised representation of "a time".
 */
public final class ClockTime {

    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_PAST_HALF = 30;
    private static final int HOURS_ON_CLOCK_FACE = 12;

    private final int hour;
    private final int minute;

    private ClockTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Creates a {@code ClockTime} from an hour and minute.
     *
     * @param hour   0-23
     * @param minute 0-59
     * @throws IllegalArgumentException if either value is out of range
     */
    public static ClockTime of(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_IN_DAY) {
            throw new IllegalArgumentException("Hour must be between 0 and 23, got: " + hour);
        }
        if (minute < 0 || minute >= MINUTES_IN_HOUR) {
            throw new IllegalArgumentException("Minute must be between 0 and 59, got: " + minute);
        }
        return new ClockTime(hour, minute);
    }

    /**
     * Parses a time in {@code "HH:MM"} format, e.g. {@code "07:35"} or {@code "23:05"}.
     *
     * @throws IllegalArgumentException if the text is not a valid {@code HH:MM} time
     */
    public static ClockTime parse(String text) {
        Objects.requireNonNull(text, "text must not be null");
        String[] parts = text.trim().split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Expected a time in HH:MM format, got: \"" + text + "\"");
        }
        try {
            return of(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected a time in HH:MM format, got: \"" + text + "\"", e);
        }
    }

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }

    /** True for 00:00 only. */
    public boolean isMidnight() {
        return hour == 0 && minute == 0;
    }

    /** True for 12:00 only. */
    public boolean isNoon() {
        return hour == 12 && minute == 0;
    }

    /** True when the minute hand is exactly on the hour. */
    public boolean isOnTheHour() {
        return minute == 0;
    }

    /** True when the minute hand is exactly on the half hour. */
    public boolean isHalfPast() {
        return minute == MINUTES_PAST_HALF;
    }

    /** True for minutes strictly between the hour and the half hour (1-29). */
    public boolean isBeforeHalfPast() {
        return minute > 0 && minute < MINUTES_PAST_HALF;
    }

    /** True for minutes strictly between the half hour and the next hour (31-59). */
    public boolean isAfterHalfPast() {
        return minute > MINUTES_PAST_HALF;
    }

    /** True when the minute value lands on a "nice" five-minute mark (0, 5, 10, ...). */
    public boolean isOnAFiveMinuteMark() {
        return minute % 5 == 0;
    }

    /** How many minutes remain until the next hour (only meaningful once past half past). */
    public int minutesToNextHour() {
        return MINUTES_IN_HOUR - minute;
    }

    /** This hour on a 12-hour clock face (1-12), e.g. 0 -&gt; 12, 13 -&gt; 1, 23 -&gt; 11. */
    public int hourOfTwelveClock() {
        return toTwelveHour(hour);
    }

    /** The next hour on a 12-hour clock face (1-12), wrapping correctly past midnight. */
    public int nextHourOfTwelveClock() {
        return toTwelveHour(hour + 1);
    }

    private static int toTwelveHour(int hour24) {
        int h = hour24 % HOURS_ON_CLOCK_FACE;
        return h == 0 ? HOURS_ON_CLOCK_FACE : h;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClockTime)) {
            return false;
        }
        ClockTime that = (ClockTime) other;
        return hour == that.hour && minute == that.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
