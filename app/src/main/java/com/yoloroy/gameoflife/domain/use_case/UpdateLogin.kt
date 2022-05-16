package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ProfileRepository

class UpdateLogin(val repository: ProfileRepository) {
    operator fun invoke(login: String) = repository.updateLogin(login)
}
