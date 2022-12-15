package com.mingdev.nasaapi.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


/*
all repo will extend from this class to use the safeApiCall functions to safely handle api responses.
*/
abstract class BaseRepo {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): NetworkResult<T> {

        // Returning api response wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {

                // Here we are calling api lambda function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    // when success response and body not nullable we are returning the body
                    // and !! is needed since we cant accept nullable for Success class

                    NetworkResult.Success(data = response.body()!!)
                } else {
                    // Simply returning generic exception with nullable response body
                    NetworkResult.Error(exception = Exception("nullable response body"))
                }

            }
            // checking various exception when failed
            catch (e: HttpException) {
                NetworkResult.Error(exception = e)
            } catch (e: IOException) {
                NetworkResult.Error(exception = e)
            } catch (e: Exception) {
                NetworkResult.Error(exception = e)
            }
        }
    }
}
