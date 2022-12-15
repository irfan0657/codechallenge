package com.example.codechallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Response dataclass
 */
class AcromineResponse : ArrayList<AcromineResponse.AcromineResponseItem>() {
    @Parcelize
    data class AcromineResponseItem(
        val lfs: List<Lf?>?,
        val sf: String?
    ) : Parcelable {
        @Parcelize
        data class Lf(
            val freq: Int?,
            val lf: String?,
            val since: Int?,
            val vars: List<Var?>?
        ) : Parcelable {
            @Parcelize
            data class Var(
                val freq: Int?,
                val lf: String?,
                val since: Int?
            ) : Parcelable

        }
    }
}