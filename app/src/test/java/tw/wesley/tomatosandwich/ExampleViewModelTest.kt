package tw.wesley.tomatosandwich

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.wesley.tomatosandwich.model.Reservation
import tw.wesley.tomatosandwich.model.TimeSlot
import tw.wesley.tomatosandwich.repos.IReservationRepository
import tw.wesley.tomatosandwich.viewmodels.CreateReservationViewModel
import tw.wesley.tomatosandwich.viewmodels.ReservationViewModel

/**
 * The purpose of the unit test here is to validate the behavior of the ViewModel
 * So we mock the repo, and see if the viewModel acts correct
 */
@ExperimentalCoroutinesApi
class ReservationViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()  // Allows immediate execution of LiveData changes

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()  // Helps with managing coroutines in tests

    private lateinit var viewModel: ReservationViewModel  // ViewModel to be tested
    private lateinit var createReservationViewModel: CreateReservationViewModel  // ViewModel to be tested
    private val mockRepo = mockk<IReservationRepository>()  // Mocked repository

    private val testScope = TestCoroutineScope()  // Scope for launching coroutines in tests

    // Mocked data set, pretend there's already an reservation
    private val mockData = MutableStateFlow(listOf(Reservation(TimeSlot(2000, false), 5, "First Guest")))

    @Before
    fun setup() {
        every { mockRepo.getReservationsFlow() } returns mockData  // Setup mock repository to return mock data
        every { mockRepo.getTimeSlots() } returns IReservationRepository.INIT_TIME_SLOTS  // Default timeslots
        coEvery { mockRepo.addReservation(any()) } answers {
            mockData.value = mockData.value + firstArg<Reservation>()  // Update mock data when a reservation is added
            true
        }

        // Create ViewModel to be tested with mocked repository
        viewModel = ReservationViewModel(mockRepo)
        createReservationViewModel = CreateReservationViewModel(mockRepo)
    }

    @After
    fun tearDown() {
        testScope.cancel()  // Clean up any active coroutines
    }

    /**
     * If I add a reservation from [CreateReservationViewModel]
     * [ReservationViewModel] shall also got the updated flow
     */
    @Test
    fun addReservation_ShouldUpdateFlow() = runTest {
        val newReservation = Reservation(TimeSlot(2030, false), 2, "Second Guest")  // New reservation to be added

        var flowContent: List<Reservation>? = null
        val job = launch {
            viewModel.reservationFlow.collect { flowContent = it }
        }

        with(createReservationViewModel) {
            timeSlot = newReservation.timeSlot
            partySize = newReservation.partySize
            guestName = newReservation.guestName
            notes = newReservation.notes
            phone = newReservation.phone
            addReservation()// Add the new reservation
        }

        coVerify { mockRepo.addReservation(newReservation) }  // Verify that addReservation method was called

        delay(500)  // Wait for flow to emit changes

        assertTrue(flowContent?.last()?.equals(newReservation) ?: false)  // Check that the new reservation is in the last of the list

        job.cancel()  // Stop collecting data from the flow
    }
}
