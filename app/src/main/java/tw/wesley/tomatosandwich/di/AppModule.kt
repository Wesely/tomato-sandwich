package tw.wesley.tomatosandwich.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.wesley.tomatosandwich.repos.IReservationRepository
import tw.wesley.tomatosandwich.repos.ReservationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideReservationRepository(): IReservationRepository {
        return ReservationRepository()
    }
}
