package com.appgim.domain.main.home.usecases

import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetActiveForms @Inject constructor(
    private val formRepository: FormRepository
) {

    suspend fun run(): List<HomeFormCard> {
        val result = formRepository.getActiveForms()
        return result.getOrElse { emptyList() }
    }

}