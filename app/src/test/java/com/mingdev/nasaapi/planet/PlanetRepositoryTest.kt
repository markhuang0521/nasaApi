package com.mingdev.nasaapi.planet

import com.google.common.truth.Truth
import com.mingdev.nasaapi.api.NasaService
import com.mingdev.nasaapi.api.model.PlanetResponse
import com.mingdev.nasaapi.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PlanetRepositoryTest {
    @MockK
    lateinit var nasaService: NasaService

    @MockK
    lateinit var response: Response<List<PlanetResponse>>

    @MockK
    lateinit var planetList: List<PlanetResponse>


    private lateinit var objUnderTest: PlanetRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        objUnderTest = PlanetRepository(
            nasaService = nasaService
        )
    }


    @Test
    fun `get random planets success`() {
        every { response.isSuccessful } returns true
        coEvery { nasaService.getRandomPlanets() } returns response
        every { response.body() } returns planetList

        runBlocking {
            val result = objUnderTest.getRandomPlanets()
            Truth.assertThat(result).isInstanceOf(NetworkResult.Success::class.java)
            result as NetworkResult.Success
            Truth.assertThat(result.data).isEqualTo(planetList)

        }

    }

    @Test
    fun `get random planets error`() {
        every { response.isSuccessful } returns false
        coEvery { nasaService.getRandomPlanets() } returns response
        runBlocking {
            val result = objUnderTest.getRandomPlanets()
            Truth.assertThat(result).isInstanceOf(NetworkResult.Error::class.java)

        }
    }
}