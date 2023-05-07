package com.example.marcioyukio_rm86662;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize;

@Parcelize
data class Pokemon (
    val name: String,
    val descricao: String,
    val skills: MutableList<String>
) : Parcelable

