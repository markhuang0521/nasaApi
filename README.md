## Video Demo

https://user-images.githubusercontent.com/40251654/208720895-86ea7b11-78ef-4310-9349-5219c9288c78.mp4


## Overview/Setup
this project is using the public nasa api https://api.nasa.gov/ as its data source. 
and serves as my default template for any new android porjects as it follows all the best practices that I know about Jetpack Compose and Android.


## Architecture
the app is using  MVVM pattern with hilt,moshi, coroutines, retrofit, jetpack compose and compose navigation.

## Feature
besides having the default list screen and the detail screen I also implemented

api call/data error handling, pull to refresh indicator, and the loading progress bar within the list screen for 
more smooth user experience.

## Testing
I'm using Mockk and Truth libraries for testing my repository/useCase methods

