package com.example.deepfaceapplication

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import interfaces.RestAPI
import kotlinx.android.synthetic.main.fragment_first.*
import models.Analyze
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var button: Button? = null
    private var imageview: ImageView? = null
    private var analyzeText: TextView? = null
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageview = view.findViewById<ImageView>(R.id.imageView2)
        analyzeText = view.findViewById(R.id.analyze_result_text)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {

            Toast.makeText(super.getContext(), "Please upload your image.", Toast.LENGTH_LONG)
                .show()
            showPictureDialog()

        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(context)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select image from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseImageFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun chooseImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data!!.data
                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
//                    saveImage(bitmap)
                    analyzePhoto(bitmap)
                    Toast.makeText(context, "Image Show!", Toast.LENGTH_SHORT).show()
                    imageview!!.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageview!!.setImageBitmap(thumbnail)
            analyzePhoto(thumbnail)

            Toast.makeText(context, "Photo Show!", Toast.LENGTH_SHORT).show()
        }
    }

    fun analyzePhoto(bitmap: Bitmap) {

        //create a file to write bitmap data
        val file = File(super.getContext()?.cacheDir, "test3");
        file.createNewFile();

//Convert bitmap to byte array
        val bos = ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        val bitmapdata = bos.toByteArray();

//write the bytes in file
        val fos: FileOutputStream? = null;
        try {
            val fos = FileOutputStream(file);
        } catch (e: FileNotFoundException) {
            e.printStackTrace();
        }
        try {
            fos?.write(bitmapdata);
            fos?.flush();
            fos?.close();
        } catch (e: IOException) {
            e.printStackTrace();
        }
//        val file = File("/home/mahsa/Downloads/profile-photo.jpeg")
        val mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        val fileToUpload =
            MultipartBody.Part.createFormData("image", file.name, mFile)


        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.connectTimeout(1000, TimeUnit.SECONDS)
        httpClient.readTimeout(2000000, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
//        Execute Request!
        val retrofit = Retrofit.Builder()
            .baseUrl("https://deepface-app.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        val uploadImage: RestAPI =
            retrofit.create(RestAPI::class.java)
        val fileUpload: Call<Analyze> =
            uploadImage.analyze(fileToUpload)
        Log.e("URL", fileUpload.request().url().toString())

        fileUpload.enqueue(object : Callback<Analyze> {
            override fun onResponse(
                call: Call<Analyze>,
                response: Response<Analyze>
            ) {
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()

                Log.e(" Javab: ", response.message())
                analyze_result_text.setText(response.message())

            }

            override fun onFailure(
                call: Call<Analyze>,
                t: Throwable
            ) {
                Log.e("Mahsa Rideman", "Error " + t.message)
                Toast.makeText(context, "Error " + t.message, Toast.LENGTH_SHORT).show()


            }
        })
        Log.e("Heivoooon", "Miay asln?")

    }

}