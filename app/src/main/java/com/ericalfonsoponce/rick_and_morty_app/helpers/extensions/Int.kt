package com.ericalfonsoponce.rick_and_morty_app.helpers.extensions

import com.ericalfonsoponce.rick_and_morty_app.data.source.remote.api.ApiError
import com.ericalfonsoponce.rick_and_morty_app.helpers.Constants

fun Int.parseErrorCode(): ApiError =
    when (this) {
        Constants.BAD_REQUEST_CODE -> ApiError.BadRequest()
        Constants.UNAUTHORIZED_CODE,
        Constants.FORBIDDEN_CODE -> ApiError.Unauthorized()
        Constants.NOT_FOUND_CODE -> ApiError.NotFound()
        else -> ApiError.Unknown()
    }