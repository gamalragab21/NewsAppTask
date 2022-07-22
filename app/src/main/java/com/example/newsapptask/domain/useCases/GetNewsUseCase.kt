package com.example.newsapptask.domain.useCases

import com.example.newsapptask.common.helpers.Resource
import com.example.newsapptask.domain.mappers.toNewsData
import com.example.newsapptask.domain.models.NewsData
import com.example.newsapptask.domain.repositories.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase@Inject constructor(
    private val mainRepository: IMainRepository,
) {

    operator fun invoke(
    ): Flow<Resource<List<NewsData>>> = flow {
        emit(Resource.Loading())
        try {
            val newsResults = mainRepository.getNewsResult()
            val result = Resource.Success(newsResults.resultDtos.map {
                it.toNewsData()
            })
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage, null))
            /*
            *  to handle all errors filed from endpoints
            * */
//            val errorResponse: ErrorResponseBody = ErrorUtils.parseError(e)
//            emit(Resource.Error(errorResponse.message, errorResponse.errors))
        }
    }
}