package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if (year != other.year) {
            year - other.year
        } else if (month != other.month) {
            month - other.month
        } else {
            dayOfMonth - other.dayOfMonth
        }
    }

    operator fun plus(interval: TimeInterval): MyDate {
        return addTimeIntervals(interval, 1)
    }

    operator fun plus(days: Int): MyDate {
        return addTimeIntervals(TimeInterval.DAY, days)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): Int {
        return when (this) {
            DAY -> i
            WEEK -> i * 7
            YEAR -> i * 365
        }
    }
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        var current: MyDate = start
        val dateRange = start..endInclusive
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean {
                return current in dateRange
            }

            override fun next(): MyDate {
                val next = current
                current = current.nextDay()
                return next
            }
        }
    }
}
