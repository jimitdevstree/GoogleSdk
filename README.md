<h1 align="center">GoogleSdk</h1>
<p align="center">
  <a href="https://jitpack.io/#Jimit-Shah23/GoogleSdk"> <img src="https://jitpack.io/v/Jimit-Shah23/GoogleSdk/month.svg" /></a>
  <a href="https://jitpack.io/#Jimit-Shah23/GoogleSdk"> <img src="https://jitpack.io/v/Jimit-Shah23/GoogleSdk.svg" /></a>
</p>
Google Simple SDK for Android which wraps original Google SDK.

Use this library for sign-in with Google it required google_service.json file in project's app module.

This is a library project which is useful for login feature added with simplest way.
Please review sample project and add into your live project. If any issue occurs then raise that issue so everyone is knowing about that issue.

## Installation
Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.Jimit-Shah23:GoogleSdk:release_version'
}
```
Step 3. **Add google_service.json file in project's app module.**

## Usage

To **initialize** the sdk class, Use below code and setListeners to
receive the callback.

```kotlin
val googleLogin = GoogleLogin()

//for login

googleLogin.logIn(activity, object : GoogleLoginResponse {
    
    override fun onSuccess(responseData: GoogleLoginResponseData){
        /* get the user account detail */
    }
    
    override fun onFailure(message: String) {
        /* handle sign in error */
    }
})
```
Set ActivityResult for default Button.
```kotlin
 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleLogin.onActivityResult(requestCode, resultCode, data)
}
```

Logout from Sign-in Google Account
```kotlin
googleLogin.logout()
```

## Author
Maintained by [Jimit Shah](https://www.github.com/Jimit-Shah23)

## Contribution

* Bug reports and pull requests are welcome.
* Make sure you use [square/java-code-styles](https://github.com/square/java-code-styles) to format your code.