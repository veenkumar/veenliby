package com.veen.veenkumar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.veen.veenkumar.network.NetworkConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment<B : ViewBinding> : DialogFragment(), CoroutineScope by CoroutineScope(
    Dispatchers.Main
) {

    protected lateinit var binding: B
        private set

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)
        
        return binding.root
    }

    override fun onDestroyView() {
        coroutineContext[Job]?.cancel()
        super.onDestroyView()
    }
}
