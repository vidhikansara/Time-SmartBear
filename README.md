# Time-SmartBear
# British Time

Converts a clock time into its British colloquial spoken form.

```
07:35  ->  twenty-five to eight
12:00  ->  noon
06:32  ->  six thirty-two
```

## Requirements

- JDK 17+
- Maven 3.6+

## Build

```
mvn clean install
```

## Run the tests

```
mvn test
```

## Run the app

```
mvn compile exec:java -Dexec.mainClass=com.britishtime.BritishTimeApp -Dexec.args="07:35 12:00 06:32"
```

Or after building the jar:

```
mvn package
java -jar target/british-time.jar 07:35 12:00 06:32
```

## Design

- **`ClockTime`** (`model`) - an immutable, validated 24-hour time. It
  answers the yes/no questions a formatter needs (`isHalfPast()`,
  `minutesToNextHour()`, `hourOfTwelveClock()`, ...) instead of exposing
  raw numbers everywhere.
- **`NumberWords`** (`words`) - spells out any number 0-59 in English.
  Shared by hours and minutes so that logic lives in one place.
- **`TimeFormattingRule`** (`format`) - five small classes, each owning one
  phrasing: `SpecialNameRule` (midnight/noon), `ExactMinuteRule`
  (o'clock/half past), `PastTheHourRule`, `ToTheHourRule`, and
  `ExactMinuteFallbackRule` (anything off the five-minute grid).
  `BritishTimeFormatter` tries them in order and uses the first match - a
  small Chain of Responsibility. Order matters only where cases overlap:
  midnight/noon must be checked before the general "on the hour" rule.
- **`BritishTimeApp`** - a thin CLI wrapper.

