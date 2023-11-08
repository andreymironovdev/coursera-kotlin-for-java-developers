package week3.taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = allDrivers
    .filter { driver -> trips.none { trip -> trip.driver == driver } }
    .toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> = allPassengers
    .map { passanger -> passanger to trips.filter { trip -> trip.passengers.contains(passanger) } }
    .filter { pair -> pair.second.size >= minTrips }
    .map { pair -> pair.first }
    .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = allPassengers
    .filter { passenger -> trips.count { trip -> trip.driver == driver && trip.passengers.contains(passenger) } > 1 }
    .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> = allPassengers
    .filter { passenger ->
        trips.count { trip ->
            trip.passengers.contains(passenger) && (trip.discount ?: 0.0) > 0
        } > trips.count { trip -> trip.passengers.contains(passenger) && (trip.discount ?: 0.0) == 0.0 }
    }
    .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    fun getRangeByTrip(trip: Trip): IntRange {
        val duration = trip.duration
        val start = 10 * (duration / 10)
        val end = start + 9
        return IntRange(start, end)
    }

    return trips
        .groupBy(keySelector = ::getRangeByTrip, valueTransform = { it })
        .maxByOrNull { entry -> entry.value.size }
        ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) {
        return false
    }

    val driversCount = allDrivers.size / 5
    val sortedIncomesByDriver = allDrivers
        .map { driver ->
            trips.filter { trip -> trip.driver == driver }.sumOf { trip -> trip.cost }
        }
        .sortedBy { it }
    val sumOf20 = sortedIncomesByDriver
        .takeLast(driversCount)
        .sum()
    val sumOf100 = sortedIncomesByDriver.sum()
    return sumOf20 >= sumOf100 * 0.8
}