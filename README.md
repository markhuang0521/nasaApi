## Overview/Setup
this project is using the public nasa api https://api.nasa.gov/ as its data source. 

when downloading the repo, please ensure to have java 11+ as the gradle jdk in order to build the app.

I'm using android studio Dolphin as the IDE of choice.



## Architecture
the app is using  MVVM pattern with hilt,moshi, coroutines, retrofit, jetpack compose and compose navigation.

## Feature
besides having the both the list screen and the detail screen I also implemented

api call/data error handling, pull to refresh indicator, and the loading progress bar within the list screen for 
more smooth user experience.

## Testing
I'm using Mockk and Truth libraries for testing my repository/useCase methods

## Limitation
1. there's a slight issue with the PullRefreshIndicator() where it isn't showing when the ui becomes to error state
but due its be fairly new api there wasn't a lot of the existing answer available online so would love know if there's better approach!
2. normally any navigation event would be part of the viewModel actions, but given the new compose navigation the navController
is centralize in one place. I decided to expose the navigation as a Lambda param.

## Summary
Overall, I'm quite satisfied with this project as it follows all the Best Practices that I currently understand and am capable of regarding Android Development. But as a developer who always wants to improve his craft any suggestion or feedback is Greatly Appreciated!
