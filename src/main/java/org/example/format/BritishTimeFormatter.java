package org.example.format;

import org.example.model.ClockTime;

import java.util.List;

/**
 * Renders a {@link ClockTime} as its British colloquial spoken form.
 *
 * <p>Holds an ordered list of {@link TimeFormattingRule}s and delegates to
 * the first one that applies - a small Chain of Responsibility. Order
 * matters: {@link SpecialNameRule} must be checked before
 * {@link ExactMinuteRule}, since midnight and noon are themselves
 * on-the-hour times with their own special names. Everything after that is
 * mutually exclusive by construction, so the rest of the order is just
 * "specific cases, then the general pattern, then the fallback".
 */
public final class BritishTimeFormatter {

    private static final List<TimeFormattingRule> RULES = List.of(
            new SpecialNameRule(),
            new ExactMinuteRule(),
            new PastTheHourRule(),
            new ToTheHourRule(),
            new ExactMinuteFallbackRule()
    );

    public String format(ClockTime time) {
        for (TimeFormattingRule rule : RULES) {
            if (rule.appliesTo(time)) {
                return rule.format(time);
            }
        }
        // Unreachable: ExactMinuteFallbackRule.appliesTo always returns true.
        throw new IllegalStateException("No formatting rule matched time: " + time);
    }
}