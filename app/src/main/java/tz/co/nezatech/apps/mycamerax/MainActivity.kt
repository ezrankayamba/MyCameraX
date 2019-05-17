package tz.co.nezatech.apps.mycamerax

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception


private const val IMAGE_CAPTURE_REQUEST = 100
private const val IMG_REFERENCE = "ImageReference"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            capturePhoto()
        }
    }

    private fun capturePhoto() {
        var intent = Intent(this, MyCameraXActivity::class.java)
        intent.putExtra(IMG_REFERENCE, System.currentTimeMillis().toString())
        startActivityForResult(intent, IMAGE_CAPTURE_REQUEST)
        //startActivity(Intent(this, MyCameraXActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_REQUEST) {
            try {
                var data = data?.extras
                if (data != null) {
                    var file = data.getString(IMG_DATA_KEY)
                    Snackbar.make(fab, "Img: $file", Snackbar.LENGTH_LONG).show()
                    image.setImageURI(Uri.parse(file))

                    data.getString(IMG_REFERENCE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
