# Tomato-Sandwich

Github repo:
Link: https://github.com/Wesely/tomato-sandwich
SSH:  `git@github.com:Wesely/tomato-sandwich.git`

Thank you for taking your time to review this project :)

I uphold the spirit of "keeping it simple". When encountering complex logic, I skip it for the time being and jot it down in the **Misc** section at the end of this document.
Sometimes, I opt for a more humorous approach. :)

Web version of this documentation? Check it out [here](https://gist.github.com/Wesely/9fb49128862abc22603641a78eec64e0).

I use a GitHub project to make my plans. They're not very detailed but I've included previews in each step. Check out the
project [here](https://github.com/users/Wesely/projects/3/views/1).

## Setup

Example Image: ![Image](https://i.imgur.com/McdsYZo.png)

1. Match the Java version in AndroidStudio. Go to Settings and search for `Gradle`.
2. Under `Gradle`, set the `Gradle JDK` to Java 17 (example: `jbr-17`).
3. Currently can build with Android Studio > Build > Build APK

## Basic Architecture

Example Image: ![Image](https://i.imgur.com/PJCiIFt.png)

- This app contains only 1 `Activity` with 5 `Fragment` and managed by `reservation_nav.xml`
- Data are shared with `ViewModels` between all `Fragment`
- All `ViewModels` manipulates data through the same `ReservationRepository` instance, injected by `Hilt`
- All Views, or saying `Fragments` collects or access the immutable data exposed by `ViewModels`
- So when ever any data is modified, all Fragments can get the same result.

## Thoughts When I'm making decision

Some of them might not used in the end.

- This time, I want to try Fragments with a navigation flow (Main, ViewDetails, SelectTime, PartySize, Details).
  - They could share the same viewModel or maybe a `Reservation` object.
  - I'm probably going to add/get reservation as two different viewModels later.
- The time slots need to be sorted.
  - I thought about writing them to RoomDB and querying them (sorted) as a flow.
  - But for simplicity, I'll use `SortedSet<Reservation>` here. Since it can't duplicate and needs to be sorted by time, it's an interesting choice.
- Reservations last for an hour:
  - We assume we don't need to clean the table.
  - Actually, I know I can just store them as String, and for each timeSlot is booked, mark the 3 slot ahead and 3 slot after as not available.
  - But I came up with a fun implementation, which is to store the time "03:30 PM" as "Integer(1530)". It would make calculating the time difference easy.
  - By doing this, I can use `(time - other.time) >= 0 && (time - other.time) <= 85` to check if it's occupied by another reservation. This 85 stands
    for `1 hour minus 15 minutes => 100 -15 = 85`.
  - It's tested in `fun testIsOccupiedBy()` and works fine.
  - The format converter is also simple: `hour = time / 100` `minutes = time % 100`.
- I wrapped the Vertical LinearLayout of the detail inputs in a scrollView for smaller devices.
  - Because when the soft keyboard appears, the screen space becomes even smaller.
  - Sometimes when we're typing we'd like to scroll a bit, so I added trailing space at the bottom of the DetailInput page.
- The application handles configuration changes (like screen rotation).
- With `lifecycleScope` we don't need to worry about the recycle in the background. Or we can assign the active lifecycle like `launchWhenResumed` ...which I didn't do.

## Misc

- I used a delegate for the viewModel `private val reservationViewModel: ReservationViewModel by viewModels()`.
  - It's said that this delegate will bind your viewModel correctly with the Activity's lifecycle.
- There's no way to cancel the appointment, oh wait, users can close the app :rofl.
- I looked into `attr` and picked some colors as the theme. But I didn't check if it's working properly this time, haha.
- I should have used `notifyItemRangeChanged()` instead of `notifyDataSetChanged()`.
- For the PartySize, there are only 5 buttons so I generated 5 buttons programmatically and put them into the ScrollView.
- I used `Timber.d()` to print logs only on debug builds.
- I could have used TextSpan to make the Reservation Details look better, but I didn't.
- I'm not setting all dimensions to `dimens.xml`.
- Wanted to import some `viewBinding` delegates so I don't have to clean them up.

