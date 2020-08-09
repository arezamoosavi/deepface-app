package models

import com.google.gson.annotations.SerializedName

class Prediction {
    @field:SerializedName("emotion")
    val emotion: Emotion? = null

    @field:SerializedName("dominant_emotion")
    val dominant_emotion: String? = null
}