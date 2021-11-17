package com.veen.veenkumar

import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar

class CommonUtils {
    companion object {

        fun longSnack(context: Context, msg: String) {
            val snackbar = Snackbar.make(
                (context as Activity).findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_LONG
            )
            snackbar.show()
        }

        fun indefiniteSnack(context: Context, msg: String) : Snackbar {
            val snackbar = Snackbar.make(
                (context as Activity).findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_INDEFINITE
            )
            snackbar.show()

            return snackbar
        }

        fun shortSnack(context: Context, msg: String) {
            val snackbar = Snackbar.make(
                (context as Activity).findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT
            )
            snackbar.show()
        }
    }
}