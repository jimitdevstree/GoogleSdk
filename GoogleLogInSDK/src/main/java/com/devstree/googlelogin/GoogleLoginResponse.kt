package com.devstree.googlelogin


/**
 * Created by Jimit Shah on 09/01/21
 */
interface GoogleLoginResponse {
    fun onSuccess(responseData: GoogleLoginResponseData)
    fun onFailure(message: String)
}