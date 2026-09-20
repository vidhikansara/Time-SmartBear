package org.example.format;

import org.example.model.ClockTime;

/**
 * A single British-English phrasing rule for a clock time.
 *
 * <p>Each implementation answers two questions: "is this my case?"
 * ({@link #appliesTo}) and "if so, what do I say?" ({@link #format}).
 * {@link BritishTimeFormatter} tries each rule in order and uses the first
 * one that says yes.
 */
public interface TimeFormattingRule {

    boolean appliesTo(ClockTime time);

    String format(ClockTime time);
}