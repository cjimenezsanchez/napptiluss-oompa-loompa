package com.jime.listdetail.domain.repository

import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.domain.model.OompaLoompaPaging

interface OompaLoompaRepository {

    suspend fun fetchOompaLoompaByPage(page: Int): Resource<OompaLoompaPaging>

    suspend fun fetchOompaLoompaById(id: String): Resource<OompaLoompaDetail>

}