package com.Faksi.UAS_10117112.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franmontiel.fullscreendialog.FullScreenDialogContent
import com.franmontiel.fullscreendialog.FullScreenDialogController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.Faksi.UAS_10117112.R

class NavigasiFragment : Fragment(), FullScreenDialogContent, OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var vie: View

    private var dialogController: FullScreenDialogController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        vie = inflater.inflate(R.layout.fragment_maps, container, false)

        return vie
    }

    override fun onConfirmClick(dialogController: FullScreenDialogController?): Boolean {
        return true
    }

    override fun onDialogCreated(dialogController: FullScreenDialogController?) {
        this.dialogController = dialogController;
    }

    override fun onDiscardClick(dialogController: FullScreenDialogController?): Boolean {
        dialogController!!.discard()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map as GoogleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-6.815118355085042, 107.62661565095186)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}
