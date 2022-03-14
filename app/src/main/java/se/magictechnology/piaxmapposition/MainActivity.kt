package se.magictechnology.piaxmapposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import se.magictechnology.piaxmapposition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.mapView.getMapAsync(this)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.markerButton.setOnClickListener {
            // 55.6112032506648, 12.994412054721224
            val minc = LatLng(55.6112032506648, 12.994412054721224)

            var themarker = MarkerOptions().position(minc).title("Minc")
            mMap.addMarker(themarker)

            var camMove = CameraUpdateFactory.newLatLngZoom(minc, 20.0F)

            mMap.moveCamera(camMove)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMarkerClickListener(this)
        mMap.setOnInfoWindowClickListener(this)

        //mMap.isMyLocationEnabled = true

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        var themarker = MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Titta lite text")

        mMap.addMarker(themarker)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onMarkerClick(clickmarker: Marker): Boolean {
        Log.i("PIAXDEBUG", "CLICK ON MARKER " + clickmarker.title)

        clickmarker.showInfoWindow()

        return true
    }

    override fun onInfoWindowClick(clickmarker: Marker) {
        Log.i("PIAXDEBUG", "CLICK ON INFO WINDOW " + clickmarker.title)

        // T.ex öppna detail vy

        clickmarker.hideInfoWindow()
    }

    override fun onMarkerDrag(p0: Marker) {
    }

    override fun onMarkerDragEnd(p0: Marker) {
    }

    override fun onMarkerDragStart(p0: Marker) {
    }


}