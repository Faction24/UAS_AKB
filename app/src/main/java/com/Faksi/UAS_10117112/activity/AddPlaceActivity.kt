package com.Faksi.UAS_10117112.activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.Faksi.UAS_10117112.R
import com.Faksi.UAS_10117112.model.WisataFirestoreModel
import com.Faksi.UAS_10117112.utils.FirestoreUtils
import kotlinx.android.synthetic.main.activity_add_place.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AddPlaceActivity : AppCompatActivity() {

    lateinit var pDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        pDialog = ProgressDialog(this)

//        init()

        tambah_lokasi.onClick {
//            pDialog.setMessage("Mohon menunggu...")
////            pDialog.setCancelable(false)
//            pDialog.show()
            FirestoreUtils.storePlace(this@AddPlaceActivity, WisataFirestoreModel(
                    nama.text.toString(),
                    deskripsi.text.toString(),
                    alamat.text.toString(),
                    sumber.text.toString(),
                    harga.text.toString(),
                    lat.text.toString(),
                    lon.text.toString(),
                    phone.text.toString(),
                    foto1.text.toString(),
                    foto2.text.toString(),
                    foto3.text.toString()
            )) {
//                pDialog.dismiss()
                finish()
            }
        }
    }

    fun init() {
        nama.setText("De Ranch Lembang, Alay Koboy Bandung")
        deskripsi.setText("De Ranch Lembang merupakan salah satu tempat liburan di Lembang yang populer. Tempatnya strategis, ngga jauh dari pusat kota Lembang sehingga mudah dijangkau. Konsep tempat wisata keluarga di Bandung ini mengusung konsep Western/Cowboy. Kita bisa bermain atau menunggang kuda, berfoto dengan kostum koboy yang tersedia, mengelilingi area dengan kuda, dan lain sebagainya.")
        alamat.setText("Jl. Maribaya No.17, Kayuambon, Lembang, Kabupaten Bandung Barat, Jawa Barat 40391.")
        sumber.setText("https://www.nativeindonesia.com/de-ranch-lembang/")
        harga.setText("50000")
        lat.setText("-6.815118355085042")
        lon.setText("107.62661565095186")
        phone.setText("082272605775")
        foto1.setText("http://2.bp.blogspot.com/-iqCx2fLX22g/U7briaUC9TI/AAAAAAAAAI4/yz8BWOwS8gw/s1600/the-ranch-lembang.jpg")
        foto2.setText("http://www.indonesia.travel/content/dam/indtravelrevamp/en/news-events/news/let-s-go-glamping-around-bandung/be9731fb57931e5a57081c5827e887a23e3c7d1a-19a07.jpg")
        foto3.setText("https://media-cdn.tripadvisor.com/media/photo-s/08/06/17/cb/de-ranch.jpg")
    }
}
