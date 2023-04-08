package com.ericalfonsoponce.rick_and_morty_app.helpers.extensions

import android.text.Html
import android.text.Spanned

fun String.fromHtml(): Spanned? =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)