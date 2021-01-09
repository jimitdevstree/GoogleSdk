package com.devstree.googlelogin

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


/**
 *  Created by Jimit Shah 09/01/21
 */

open class GoogleLogin {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    var activity: Activity? = null
    private var callBack: GoogleLoginResponse? = null
    val RC_SIGN_IN: Int = 10101

    /**
     * Login to Google
     *
     * @param activity
     * @param GoogleLoginResponse
     */

    fun logIn(activity: Activity, callBack: GoogleLoginResponse) {
        this.activity = activity
        this.callBack = callBack
        googleSignIn()
    }

    /**
     * Logout from Google
     */

    fun logout() {
        mGoogleSignInClient.signOut()
            ?.addOnCompleteListener(activity!!) {
                Toast.makeText(activity, "SignOut Successful", Toast.LENGTH_LONG)
                    .show()
            }
    }

    /**
     * Start the Google account intent
     */

    private fun googleSignIn() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val accountData: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(activity)
        if (accountData != null) {
            handleSignInResult(accountData)
            return
        } else {
            mGoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(activity!!, signInIntent, RC_SIGN_IN, null)
        }
    }

    /**
     * Store the last sign-in account in GoogleLoginResponseData
     */

    private fun handleSignInResult(account: GoogleSignInAccount?) {
        if (account == null) return
        if (account.id == null) return

        val loginData = GoogleLoginResponseData()
        loginData.email = account.email.orEmpty()
        loginData.name = account.displayName.orEmpty()
        loginData.loginId = account.id.orEmpty()
        loginData.profilePic = account.photoUrl?.path.orEmpty()

//        Toast.makeText(activity, "Sign-in Successful", Toast.LENGTH_SHORT).show()

        callBack?.onSuccess(loginData)


    }

    /**
     * Call this inside your activity in [Activity.onActivityResult]
     * method
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            if (resultCode == RESULT_OK && data != null) {
                val account =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                        .getResult(ApiException::class.java)
                handleSignInResult(account)
            }
        } catch (e: ApiException) {
           callBack?.onFailure("SignInResult fail code=${e.statusCode}")
        }
    }
}