package com.jime.listdetail.presentation.repository

import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.model.OompaLoompaPaging
import com.jime.listdetail.domain.repository.OompaLoompaRepository
import com.jime.listdetail.domain.error.Error

class FakeRepository : OompaLoompaRepository {

    override suspend fun fetchOompaLoompaByPage(page: Int): Resource<OompaLoompaPaging> {
        return Resource.Success(
            OompaLoompaPaging(0, 0, emptyList())
        )
    }

    override suspend fun fetchOompaLoompaById(id: Int): Resource<OompaLoompaDetail> {
        return Resource.Failure(error = Error.Network)
    }
}