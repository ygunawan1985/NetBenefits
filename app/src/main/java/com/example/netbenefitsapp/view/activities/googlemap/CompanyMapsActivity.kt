package com.example.netbenefitsapp.view.activities.googlemap

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.User

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CompanyMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mUser : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_maps)

        mUser = intent.getParcelableExtra("user")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))
        supportActionBar?.title = mUser?.company + " Google Maps"

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var address = ""

        when(mUser?.company) {
            "Mobile Apps Company" -> address = "1785 The Exchange SE, Atlanta, GA 30339"
            "Starbucks" -> address = "2401 Utah Avenue South, Seattle, WA 98134"
            "Nike" -> address = "One Bowerman Drive, Beaverton, OR 97005"
            "Microsoft" -> address = "One Microsoft Way, Redmond, WA 98052"
        }

        // Add a marker in Sydney and move the camera
        val place = getLatLngByAddress(address)
        mMap.addMarker(MarkerOptions().position(place).title(mUser?.company))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        mMap.setMinZoomPreference(15.0f)
    }

    private fun getLatLngByAddress(address : String) : LatLng {
        val geocoder = Geocoder(this)
        val addressInfo : Address = geocoder.getFromLocationName(address, 1)[0]
        val lat = addressInfo.latitude
        val lon = addressInfo.longitude
        return LatLng(lat, lon)
    }
}
