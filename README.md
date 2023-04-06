<h1 align="center">ShopTestApp</h1>

<p align="center">  
<H4><a href="https://docs.google.com/document/d/1X92XL_aIOJzmk87Tg2rqqE836jHXjR-M/edit">Technical specifications</a></H1>
<H4><a href="https://www.figma.com/file/H0SE8wvK5kIhQlZVxp0BNj/Online-Shop-Satria-Adhi-Pradana-(Community)?node-id=1-130&t=RvzJoEbUqIrhEgv0-0">Figma</a></H1>
</p>
</br>


<table style="width: 100%;">
  <tr>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339127-76bbb63f-d9ba-49df-8bf4-234687eb1c34.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339212-53b9a4fe-14cd-415c-a7a4-288a5f705733.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339241-b5f77619-4f50-4cdb-a66d-941ff4dbfd9a.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339267-2c3b82ff-f221-431a-9b9a-b84aaec80499.png"/></td>
  </tr>
</table>

<table style="width: 100%;">
  <tr>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339342-b340a050-abd0-43d5-903f-3ce589c3d848.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339434-4627b99b-1eba-415c-8f12-a663505ac6ad.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339387-c75ce80a-8ae1-4bcd-a8cf-09187688b40a.png"/></td>
    <td><img src="https://user-images.githubusercontent.com/40930427/230339482-ab5043e7-a9fa-47bc-8f6e-45b8b6fb7d6c.png"/></td>
  </tr>
</table>

<p align="center">
<video src="https://user-images.githubusercontent.com/40930427/222537155-78082171-791f-4aa0-a0f2-c660676ff5e7.mp4" controls></video>
</p>

## Download
Go to the https://drive.google.com/file/d/1TnJZ2auMlWc3ltFHRQBbRyWI09zCPwlX/view?usp=share_link to download the APK.

## Tech stack & Open-source libraries
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Compose: Android’s modern toolkit for building native UI.
  - ViewModel: UI related data holder and lifecycle aware.
  - Navigation: For navigating screens and [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for injecting dependencies.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): Dependency Injection.
- Architecture
  - MVI/MVVM Architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich): Construct a lightweight and modern response interface to handle network payload for Android.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Material-Components](https://github.com/material-components/material-components-android): Material design components for building ripple animation, and CardView.
- [Glide](https://github.com/bumptech/glide)
- Build
  - BuildSrc
- Lint
  - Ktlint
  - Detekt
- Test
  - Junit
  - Mockk
  - LeakCanary
