package com.jime.listdetail.data.repository

import com.jime.listdetail.data.mapper.toDomain
import com.jime.listdetail.data.remote.OompaLoompaApi
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.di.IoDispatcher
import com.jime.listdetail.domain.error.ErrorHandler
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OompaLoompaRepositoryImp @Inject constructor(
    private val api: OompaLoompaApi,
    private val errorHandler: ErrorHandler,
    @IoDispatcher private val iODispatcher: CoroutineDispatcher = Dispatchers.IO
) : OompaLoompaRepository {

    override suspend fun fetchOompaLoompaByPage(page: Int): Resource<OompaLoompaPaging> =
        withContext(iODispatcher) {
            try {
                val result = api.getOompaLoompaByPage(page).toDomain()
                Resource.Success(result)

            } catch (t: Throwable) {
                Resource.Failure(
                    error = errorHandler.getError(t),
                    data = OompaLoompaPaging(
                        totalPages = -1,
                        currentPage = page,
                        oompaLoompaList = emptyList()
                    )
                )
            }
        }

    override suspend fun fetchOompaLoompaById(id: Int): Resource<OompaLoompaDetail> =
        withContext(iODispatcher) {
            try {
                val result = api.getOompaLoompaById(id).toDomain()
                Resource.Success(result)
            } catch (t: Throwable) {
                Resource.Failure(errorHandler.getError(t))
            }
        }
}