# Fitness App

## Description
FitnessApp is an android application that lets user set normal timer for exercise, set repeated timer for exercises with 
multiple sets and lets user view list of exercises for inspiration. Exercises are downloaded from server that is also
included in this github page. FitnessApp also lets user set dark mode and change signal that gets played when timer stops.

## App installation
1) Download Android studio from https://developer.android.com/studio
2) Download code from github by typing git clone https://github.com/1ronalds/FitnessApp.git
3) Open project in android studio. Folder for android app is /PhoneApp
4) Install android emulator for display purpouses. Press 'Run app' button to launch app on emulator.
To create .apk app for phone open Build > Build bundle(s) APK(s) > Build APK(s).

## Server installation (on remote server)
1) Set up a linux server and connect to it with ssh.
2) Install MySQL server and java on it. (password for sql server should be '' and user 'root')
3) On your machine download all code with git clone https://github.com/1ronalds/FitnessApp.git and then open /fitness-app
4) Open terminal tab, go back to project directory and type mvnw package
5) In /fitness-app/target folder there will be .jar file of all code, move it to server with scp command
6) Run server with java -jar file.jar & and then type ctrl+z, connect again, server will be running in background.
7) Import sql file from github main page by following these instructions:
https://www.digitalocean.com/community/tutorials/how-to-migrate-a-mysql-database-between-two-servers
8) Edit android application's code to reflect new server
