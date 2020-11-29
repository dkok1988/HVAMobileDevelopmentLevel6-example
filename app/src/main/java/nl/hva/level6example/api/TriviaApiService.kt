package nl.hva.level6example.api

import nl.hva.level6example.model.Trivia
import retrofit2.http.GET

interface TriviaApiService {

    @GET("/random/trivia?json")
    suspend fun getRandomNumberTrivia(): Trivia
}
