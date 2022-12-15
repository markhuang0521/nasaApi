package com.mingdev.nasaapi.planet

import com.google.common.truth.Truth
import com.mingdev.nasaapi.api.model.PlanetResponse
import com.mingdev.nasaapi.planet.model.Planet
import com.mingdev.nasaapi.util.Lce
import com.mingdev.nasaapi.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PlanetUseCaseTest {


    @MockK
    lateinit var planetRepository: PlanetRepository

    @MockK
    lateinit var planetResult: NetworkResult.Success<List<PlanetResponse>>
    @MockK
    lateinit var planetResultError: NetworkResult.Error

    @MockK
    lateinit var planetResponse: List<PlanetResponse>

    @MockK
    lateinit var lcePlanetList: Lce<List<Planet>>
    @MockK
    lateinit var lcePlanetError: Lce.Error

    private val successBlock = slot<((List<PlanetResponse>) -> Lce<List<Planet>>)>()

    private lateinit var objUnderTest: PlanetUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        objUnderTest = PlanetUseCase(
            planetRepository = planetRepository
        )
    }

    @Test
    fun `get Random Planets success`() {
        coEvery { planetRepository.getRandomPlanets() } returns planetResult

        every { planetResult.data } returns planetResponse

        every {
            planetResult.map(
                successBlock = capture(successBlock),
                errorBlock = captureLambda()
            )
        } returns lcePlanetList

        runBlocking {
            val result = objUnderTest.getRandomPlanets()
            Truth.assertThat(result).isInstanceOf(Lce.Content::class.java)
            Truth.assertThat(result).isEqualTo(lcePlanetList)

        }

    }
    @Test
    fun `get Random Planets fail`() {
        coEvery { planetRepository.getRandomPlanets() } returns planetResultError

        every {
            planetResultError.map<Lce<List<Planet>>>(
                successBlock = captureLambda(),
                errorBlock = captureLambda()
            )
        } returns lcePlanetError

        runBlocking {
            val result = objUnderTest.getRandomPlanets()
            Truth.assertThat(result).isInstanceOf(Lce.Error::class.java)

        }

    }

}