package com.mohitsharma.virtualrun.others

import android.Manifest
import android.content.Context
import android.os.Build
import pub.devrel.easypermissions.EasyPermissions
object trackingUtils {

    fun hasLocationPermission(context: Context) =
       if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
           EasyPermissions.hasPermissions(
               context,
               Manifest.permission.ACCESS_COARSE_LOCATION,
               Manifest.permission.ACCESS_FINE_LOCATION
           )
       }else{
           EasyPermissions.hasPermissions(
               context,
               Manifest.permission.ACCESS_COARSE_LOCATION,
               Manifest.permission.ACCESS_FINE_LOCATION,
               Manifest.permission.ACCESS_BACKGROUND_LOCATION
           )
       }
}