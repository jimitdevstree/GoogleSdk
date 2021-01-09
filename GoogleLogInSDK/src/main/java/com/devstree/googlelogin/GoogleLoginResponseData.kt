package com.devstree.googlelogin

import android.net.Uri
import java.io.Serializable

/**
 * Created by Jimit Shah on 09/01/21
 */
class GoogleLoginResponseData : Serializable {

    var loginId: String = ""
    var name: String = ""
    var email: String = ""
    var mobile: String = ""
    var gender: String = ""
    var birthday: String = ""
    var profilePic: String = ""

}