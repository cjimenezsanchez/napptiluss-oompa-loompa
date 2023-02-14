package com.jime.listdetail.data.repository

import com.jime.listdetail.data.mapper.toDomain
import com.jime.listdetail.data.remote.OompaLoompaApi
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.error.ErrorHandler
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import javax.inject.Inject

class OompaLoompaRepositoryImp @Inject constructor(
    private val remoteDataSource: OompaLoompaApi,
    private val errorHandler: ErrorHandler,
) : OompaLoompaRepository {

    override suspend fun fetchOompaLoompaByPage(page: Int): Resource<OompaLoompaPaging> {
        return try {
            val result = remoteDataSource.getOompaLoompaByPage(page).toDomain()
            Resource.Success(result)
        } catch (t: Throwable) {
            Resource.Failure(errorHandler.getError(t))
        }
    }

    override suspend fun fetchOompaLoompaById(id: Int): Resource<OompaLoompaDetail> {
        return try {
            val result = remoteDataSource.getOompaLoompaById(id).toDomain()
            Resource.Success(result)
        } catch (t: Throwable) {
            Resource.Failure(errorHandler.getError(t))
        }
    }
}