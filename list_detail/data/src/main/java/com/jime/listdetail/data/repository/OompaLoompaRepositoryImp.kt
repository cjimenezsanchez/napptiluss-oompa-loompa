package com.jime.listdetail.data.repository

import com.jime.listdetail.data.mapper.toDomain
import com.jime.listdetail.data.remote.OompaLoompaApi
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import javax.inject.Inject

class OompaLoompaRepositoryImp @Inject constructor(
    private val remoteDataSource: OompaLoompaApi
) : OompaLoompaRepository {

    override suspend fun fetchOompaLoompaByPage(page: Int): OompaLoompaPaging {
        return remoteDataSource.getOompaLoompaByPage(page).toDomain()
    }

    override suspend fun fetchOompaLoompaById(id: String): OompaLoompaDetail {
        return remoteDataSource.getOompaLoompaById(id).toDomain()
    }
}