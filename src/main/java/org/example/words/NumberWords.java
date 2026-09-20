package org.example.words;

/**
 * Spells out an integer from 0 to 59 in English words.
 *
 * <p>That range is deliberate: it is exactly what a clock ever needs to say
 * out loud, whether that number is an hour (1-12) or a minute (0-59), so a
 * single small converter can serve both without ever needing to handle
 * "hundred", "thousand", and so on.
 */
public final class NumberWords {

    private static final int MAX_SUPPORTED = 59;

    private static final String[] ONES = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    private static final String[] TEENS = {
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "forty", "fifty"
    };

    private NumberWords() {
        // Utility class: not meant to be instantiated.
    }

    /**
     * Spells out {@code number} in English words, e.g. {@code 32} -&gt; {@code "thirty-two"}.
     *
     * @throws IllegalArgumentException if {@code number} is outside 0-59
     */
    public static String of(int number) {
        if (number < 0 || number > MAX_SUPPORTED) {
            throw new IllegalArgumentException(
                    "Only numbers from 0 to " + MAX_SUPPORTED + " are supported, got: " + number);
        }
        if (number < 10) {
            return ONES[number];
        }
        if (number < 20) {
            return TEENS[number - 10];
        }
        int tens = number / 10;
        int ones = number % 10;
        return ones == 0 ? TENS[tens] : TENS[tens] + "-" + ONES[ones];
    }
}

