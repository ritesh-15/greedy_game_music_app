# Music app

## Features

1. Choose a specific genre from the list of available genres
2. Details of specific genres including Artists, Albums and Tracks
3. Clicking on each album show the album details including album name, album image, album artist, genres and description
4. Clicking on artist display the details of artist including artist name, artist image,
   top tracks and top artists

## Architecture used

Used MVVM (Model View View Model Architecture) which separates data presentation logic from business logic and it also easier for unit testing

## Tech stack used

1. Kotlin
2. Retrofit
3. Material Design

## Assumptions

1. There is only one image available for all the artists and tracks hence used random image api for colour full images
2. Error handling not done properly due to time constraints
3. Loading of content is not done properly

## Futher improvements

1. Good loading animation while api content is loading
2. Robust error handling
3. Unit testing each activity
4. Dark mode support
