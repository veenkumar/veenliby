package com.veen.veenkumar

import android.content.Context
import android.util.Log
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import java.io.File

class S3Bucket(
    private val mContext: Context,
    private val mFilePath: String,
    private val mUploadListener: UploadListener?
) {
    private var amazonS3Client: AmazonS3Client? = null

    interface UploadListener {
        fun onUploadComplete(url: String?)
    }

    private fun initializeAmazonCredentialProvider() {
        // Initialize the Amazon Cognito credentials provider
        val credentialsProvider = CognitoCachingCredentialsProvider(
            mContext,
            IDENTITY_POOL_ID,  // Identity pool ID
            Regions.AP_SOUTH_1 // Region
        )
        TransferNetworkLossHandler.getInstance(mContext.applicationContext)
        amazonS3Client = AmazonS3Client(credentialsProvider)
        uplaodFile()
    }

    fun uplaodFile() {
        val file = File(mFilePath)
        val transferObserver =
            TransferUtility(amazonS3Client, mContext).upload(BUCKET_NAME, file.name, file)
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    val url = "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + transferObserver.key
                    mUploadListener?.onUploadComplete(url)
                } else if (state == TransferState.FAILED) {
                    Log.d(TAG, "onStateChanged: FAILED..")
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                val percentage = bytesCurrent.toFloat() / bytesTotal.toFloat() * 100
                Log.d(TAG, "onProgressChanged: UPLOAD PERCENT => $percentage")
            }

            override fun onError(id: Int, ex: Exception) {
                Log.e(TAG, "onError: " + ex.message, ex)
            }
        })
    }

    companion object {
        private const val TAG = "S3Bucket"
        const val IDENTITY_POOL_ID = "ap-south-1:92c450ed-2231-4909-8be9-6ac4a6fa9005"
        const val BUCKET_NAME = "bhadaexpress"
    }

    init {
        initializeAmazonCredentialProvider()
    }
}