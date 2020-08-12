package com.Faksi.UAS_10117112.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.Faksi.UAS_10117112.R
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var titleMap: String
    private var latMap: Double? = null
    private var lonMap: Double? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        titleMap = intent.getStringExtra("nama")
        latMap = intent.getStringExtra("lat").toDouble()
        lonMap = intent.getStringExtra("lon").toDouble()

        wisata_btn.apply {
            text = titleMap
            onClick {
                val location = LatLng(latMap!!, lonMap!!)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
            }
        }

        gmaps_btn.onClick {
            val url = "http://maps.google.com/maps?&z=10&q=$latMap+$lonMap(Pool+Location)&mrt=yp"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        // map setting
        mMap = googleMap
        mMap.setOnMarkerClickListener(this)
        mMap.isMyLocationEnabled = true

        // Add place mark
        val location = LatLng(latMap!!, lonMap!!)
        mMap.addMarker(MarkerOptions().position(location).title("Lokasi Wisata")).showInfoWindow()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))


//        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
//            if (location != null) {
//                lastLocation = location
//                val currentLatLng = LatLng(location.latitude, location.longitude)
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
//            }
//        }
    }

    override fun onMarkerClick(p0: Marker?) = false

}
