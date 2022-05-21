package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class UpdatePassword @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) = repository.updatePassword(oldPassword, newPassword, newPasswordConfirmation)
}
