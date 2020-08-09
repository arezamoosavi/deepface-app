package models

import com.google.gson.annotations.SerializedName

class Analyze {
    @field:SerializedName("file_size")
    var file_size: Double? = null

    @field:SerializedName("prediction")
    var prediction: Prediction? = null
}