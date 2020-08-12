package com.Faksi.UAS_10117112.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import com.Faksi.UAS_10117112.R
import com.Faksi.UAS_10117112.adapter.DetailFotoAdapter
import com.Faksi.UAS_10117112.model.WisataFotoModel
import com.Faksi.UAS_10117112.utils.FirestoreUtils
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick


class DetailActivity : AppCompatActivity() {

    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var nama: String? = null
    private var lat: String? = null
    private var lon: String? = null
    private var phone: String? = null
    private lateinit var pDialog: ProgressDialog

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val id = intent.getStringExtra("id")

        pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.setOnCancelListener {
            finish()
        }
        pDialog.show()
        loadLokasi(id)

        navigasi.onClick {
            startActivity(intentFor<MapsActivity>(
                    "nama" to nama,
                    "lat" to lat,
                    "lon" to lon
            ))
        }

        call.onClick {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:"+phone)
            startActivity(callIntent)
        }

        setUpMap()
        setUpCall()
    }

    fun loadLokasi(id: String) {
        FirestoreUtils.getLokasiDetail(id) {
            toolbar.title = it.get("nama").toString()

            val foto = ArrayList<WisataFotoModel>()

            foto.add(WisataFotoModel(it.get("foto1").toString()))
            foto.add(WisataFotoModel(it.get("foto2").toString()))
            foto.add(WisataFotoModel(it.get("foto3").toString()))

            loadProdukFoto(foto)
            sumber.text = it.get("sumber").toString()
            alamat.text = it.get("alamat").toString()
            deskripsi.text = it.get("deskripsi").toString()
            nama = it.get("nama").toString()
            lat = it.get("lat").toString()
            lon = it.get("lon").toString()

            pDialog.dismiss()
        }
    }

    fun loadProdukFoto(foto: ArrayList<WisataFotoModel>) {
        val sliderAdapter = DetailFotoAdapter(this, foto)
        viewPager.setAdapter(sliderAdapter)

        dotscount = sliderAdapter.getCount()
        dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots!![i] = ImageView(this@DetailActivity)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_nonactive))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(5, 0, 5, 0)
            viewPager_dots.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_active))

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_nonactive))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_active))

                if (position == dotscount) {
                    viewPager.setCurrentItem(0)
                }

            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        } else {
            navigasi.isEnabled = true
        }
    }

    private fun setUpCall() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CALL_PHONE), 2)
            return
        } else {
            call.isEnabled = true
        }
    }

}
