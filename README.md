# tomato-sandwich

## Setup

1. Align the Java version that AndroidStudio >> Settings, Search for `Gradle`.
2. Under `Gradle`, sed the `Gradle JDK` to Java 17 (example: `jbr-17`)

## Thoughts

- The time slots need to be sorted
    - I came up with write them to RoomDB and query them (sorted) as a flow
    - But for the Simplest Spirit, I'll use `SortedSet<Reservation>` here. Since it shall not duplicate, and needs to be sorted by time, I think it's an interesting choice.
- Reservations lasts for an hour:
    - Assume we don't need to clean the table
    - I think up a funny implementation, which is store the time "03:30 PM" as "Integer(1530)". It would be easy to calculate the time difference.
- This time I'd like to try Fragments with navigation flow (Main, ViewDetails, SelectTime, PartySize, Details)
    - they can share a same viewModel or maybe a `Reservation` object
    - I'll probably gonna make add/get reservation as 2 different viewModel later
- Wrap Vertical Linearlayout of the detail inputs in a scrollView for some smaller devices.
    - Because when soft keyboard shows, the screen space is even smaller
    - Sometimes when we're typing we'd like to scroll a bit, so add trailing space at the bottom of the DetailInput page

## Misc

- Use delegate for viewModel `private val reservationViewModel: ReservationViewModel by viewModels()`
    - It's said that this delegate would bind your viewModel properly with the Activity's lifecycle.
- There's no way to cancel the appointment, oh wait, they can close the app :rofl
- Looked into `attr` and picked some colors as Theme. But not gonna check if it's working properly this time, haha.
- I should go with `notifyItemRangeChanged()` not `notifyDataSetChanged()`
- For the PartySize, there are only 5 buttons so I programmatically generate 5 Button and put them into the ScrollView
- User `Timber.d()` to print logs only on debug builds