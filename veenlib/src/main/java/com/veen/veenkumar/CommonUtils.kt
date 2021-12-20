package com.veen.veenkumar

import android.app.Activity
import android.content.Context
import com.google.android.material.snackbar.Snackbar

class CommonUtils {
    companion object {
        
        fun Fragment.hideKeyboard() {
            view?.let { activity?.hideKeyboard(it) }
        }

        fun Activity.hideKeyboard() {
            hideKeyboard(currentFocus ?: View(this))
        }

        fun Context.hideKeyboard(view: View) {
            val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

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
