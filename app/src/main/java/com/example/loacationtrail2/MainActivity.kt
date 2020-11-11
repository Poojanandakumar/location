package com.example.loacationtrail2

import android.Manifest
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),5)
            }
            return
        }
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        val provider = locationManager.getBestProvider(criteria, false)
        val enabled =
                if (provider != null) {
                    locationManager.isProviderEnabled(provider)
                } else
                    false
        Toast.makeText(this, "location enabled $enabled", Toast.LENGTH_SHORT).show()
        val text : TextView = findViewById(R.id.text_view)
        if (enabled && provider!=null)
           locationManager.requestLocationUpdates(provider,1000,1f,
               {
                   val location2:Location = Location("loc")
                   location2.latitude = 10.69327156
                   location2.longitude = 76.09167734
                   val distance = it.distanceTo(location2)

                   text.text="Distance is $distance ${it.latitude}, ${it.longitude}"

               },mainLooper)



    }


}
