package com.application.custommapmarker

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.getSystemService
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mapFragment: SupportMapFragment?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMap()
    }

    private fun setupMap(){
        mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLon1= LatLng(41.077577, 28.918875)
        val latLon2= LatLng(41.029791, 29.054209)

        val markerView= (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.marker_layout, null)
        val text= markerView.findViewById<TextView>(R.id.markerText)
        val cardView= markerView.findViewById<CardView>(R.id.markerCardView)
        val image= markerView.findViewById<ImageView>(R.id.markerImage)

        text.text= "I am here !"
        val bitmap1= Bitmap.createScaledBitmap(viewToBitmap(cardView)!!, cardView.width, cardView.height, false)
        val smallMarkerIcon1= BitmapDescriptorFactory.fromBitmap(bitmap1)
        googleMap.addMarker(MarkerOptions().position(latLon1).icon(smallMarkerIcon1))

        text.text= "I am here too!"
        val bitmap2= Bitmap.createScaledBitmap(viewToBitmap(cardView)!!, cardView.width, cardView.height, false)
        val smallMarkerIcon2= BitmapDescriptorFactory.fromBitmap(bitmap2)
        googleMap.addMarker(MarkerOptions().position(latLon2).icon(smallMarkerIcon2))
    }

    private fun viewToBitmap(view: View): Bitmap?{
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap= Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas= Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
    }
}