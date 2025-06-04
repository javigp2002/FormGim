package com.appgim.domain.main.home.usecases

import com.appgim.domain.auth.repositories.UserRepository
import com.appgim.domain.main.home.models.HomeFormCard
import com.appgim.domain.main.home.repositories.FormRepository
import javax.inject.Inject

class GetDoneForms @Inject constructor(
    private val formRepository: FormRepository,
    private val userRepository: UserRepository
) {

    suspend fun run(): List<HomeFormCard> {
        val user = userRepository.getUser() ?: return emptyList()
        val result = formRepository.getDoneForms(user.id)
        return result.getOrElse { emptyList() }
    }

}