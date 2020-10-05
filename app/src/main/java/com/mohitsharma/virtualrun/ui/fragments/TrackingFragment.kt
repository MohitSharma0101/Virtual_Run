package com.mohitsharma.virtualrun.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.PolylineOptions
import com.mohitsharma.virtualrun.R
import com.mohitsharma.virtualrun.others.Constants.ACTION_PAUSE_SERVICE
import com.mohitsharma.virtualrun.others.Constants.ACTION_START_OR_RESUME_SERVICE
import com.mohitsharma.virtualrun.others.Constants.MAP_ZOOM
import com.mohitsharma.virtualrun.others.Constants.POLYLINE_COLOR
import com.mohitsharma.virtualrun.others.Constants.POLYLINE_WIDTH
import com.mohitsharma.virtualrun.service.PolyLine
import com.mohitsharma.virtualrun.service.PolyLines
import com.mohitsharma.virtualrun.service.TrackingService
import com.mohitsharma.virtualrun.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tracking.*

@AndroidEntryPoint
class TrackingFragment: Fragment(R.layout.fragment_tracking) {

    private var isTracking = false

    private var pathPoints = mutableListOf<PolyLine>()

    private val viewModel: MainViewModel by viewModels()
    private var map :GoogleMap? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToggleRun.setOnClickListener{
           toggleRun()
        }
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            map = it
            addAllPolyline()
        }
        subscribeToUser()
    }

    private fun subscribeToUser(){
      TrackingService.isTracking.observe(viewLifecycleOwner, Observer {
          updateTracking(it)
      })
        TrackingService.pathPoints.observe(viewLifecycleOwner, Observer {
            pathPoints = it
            addLatestPolyLine()
            moveCameraToUser()
        })
    }

    private fun toggleRun(){
        if(isTracking){
            sendCommandToService(ACTION_PAUSE_SERVICE)
        }else{
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
    }
    private fun updateTracking(isTracking : Boolean){
        this.isTracking = isTracking
        if(!isTracking){
            btnToggleRun.text = "Start"
            btnFinishRun.visibility = View.VISIBLE
        }else{
            btnFinishRun.visibility = View.GONE
            btnToggleRun.text = "Stop"
        }
    }

    private fun moveCameraToUser(){
        if(pathPoints.isNotEmpty() && pathPoints.last().isNotEmpty()){
            map?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    pathPoints.last().last(),
                    MAP_ZOOM
                )
            )
        }
    }

    private fun addAllPolyline(){
        for(polyline in pathPoints){
            val polygonOptions = PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .addAll(polyline)

            map?.addPolyline(polygonOptions)
        }
    }

    private fun addLatestPolyLine(){
        if(pathPoints.isNotEmpty() && pathPoints.last().size > 1){
            val preLastLatLng = pathPoints.last()[pathPoints.last().size-2]
            val lastLatLng = pathPoints.last().last()
            val polygonOptions = PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .add(preLastLatLng)
                .add(lastLatLng)

            map?.addPolyline(polygonOptions)
        }
    }

    private fun sendCommandToService(action: String)=
        Intent(requireContext(),TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
}