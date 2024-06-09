package com.example.opsc7311poe

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class Profile_activity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    //photo stuffs
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_PICK = 2
    private val PERMISSION_REQUEST_CODE = 100

    private var photoURI: Uri? = null
    private lateinit var ibtnPhotoChoose: ImageButton
    private var currentPhotoPath: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNav = findViewById(R.id.bottomNav)!!
        bottomNav.selectedItemId = R.id.profile
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.profile -> {
                    startActivity(Intent(this, Profile_activity::class.java))
                    true
                }

                R.id.calendar -> {
                    startActivity(Intent(this, TaskCalendar_activity::class.java))
                    true
                }

                R.id.timer -> {
                    startActivity(Intent(this, Timer_activity::class.java))
                    true
                }

                else -> {
                    false
                }
            }
        }

        //Initialisation of Inputs
        val nameEdit: TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit: TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit: TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit: TextView = findViewById(R.id.edtPasswordEdit)
        ibtnPhotoChoose = findViewById(R.id.ibtnPhoto)

        val saveBut: Button = findViewById(R.id.btnSaveProfile)

        setContent()

        ibtnPhotoChoose.setOnClickListener {
            showPhotoDialog()
        }


        //onClickListener for Save Button
        saveBut.setOnClickListener {
            val changedFullname = nameEdit.text.toString()
            val changedEmail = emailEdit.text.toString()
            val changedUserName = userNameEdit.text.toString()
            val changedPassword = PassEdit.text.toString()

            if (changedFullname.isEmpty() || changedEmail.isEmpty() || changedUserName.isEmpty() || changedPassword.isEmpty()) {
                // Show a Toast message indicating that all fields are required
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Iterate through the UserList to find the user with the matching username
            UserList.users.forEach { user ->
                if (user.username == SessionUser.currentUser?.username) {
                    // Update the user object with the changed details
                    user.apply {
                        username = changedFullname
                        email = changedEmail
                        // Assuming you want to update the username field
                        username = changedUserName
                        password = changedPassword
                    }
                    SessionUser.currentUser = user
                    // Exit the loop once the user is updated
                    return@setOnClickListener
                }
            }
            setContent()
        }


    }

    //Method to update Users data using captured inputs
    private fun setContent() {
        val nameEdit: TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit: TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit: TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit: TextView = findViewById(R.id.edtPasswordEdit)

        nameEdit.hint = SessionUser.currentUser?.fullName
        emailEdit.hint = SessionUser.currentUser?.email
        userNameEdit.hint = SessionUser.currentUser?.username
        PassEdit.hint = SessionUser.currentUser?.password
    }


    //Photo taking/upload bs

    private fun showPhotoDialog() {
        val options = arrayOf("Take Photo", "Upload from Device")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an option")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    if (checkPermissions()) {
                        takePhoto()
                    } else {
                        checkPermissions()
                    }
                }

                1 -> {
                    if (checkPermissions()) {
                        pickImageFromGallery()
                    } else {
                        checkPermissions()
                    }
                }
            }
            builder.show()
        }
    }

    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val readStoragePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val writeStoragePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val listPermissionsNeeded = mutableListOf<String>()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
            return false
        }
        return true
    }


    @SuppressLint("QueryPermissionsNeeded")
    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "${applicationContext.packageName}.provider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    ibtnPhotoChoose.setImageURI(photoURI)
                }

                REQUEST_IMAGE_PICK -> {
                    photoURI = data?.data
                    ibtnPhotoChoose.setImageURI(photoURI)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // Permissions granted, proceed with the action
                } else {
                    Toast.makeText(this, "Permissions required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



