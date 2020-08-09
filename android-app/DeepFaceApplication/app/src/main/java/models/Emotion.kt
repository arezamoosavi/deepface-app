package models

import com.google.gson.annotations.SerializedName

class Emotion {

    @field:SerializedName("angry")
    val angry: Double? = null

    @field:SerializedName("disgust")
    val disgust: Double? = null

    @field:SerializedName("fear")
    val fear: Double? = null

    @field:SerializedName("happy")
    val happy: Double? = null

    @field:SerializedName("sad")
    val sad: Double? = null

    @field:SerializedName("surprise")
    val surprise: Double? = null

    @field:SerializedName("neutral")
    val neutral: Double? = null

}