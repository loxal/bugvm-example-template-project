# RoboVM Template Project
An OOB working *RoboVM* template project for *IntelliJ IDEA*, **Maven**, and **Gradle** in order to build iOS apps.
Currently it’s not possible to start the application from IntelliJ IDEA.

## Getting Started
* Prerequisites
	* Xcode
	* Java JDK 7

* Run with Maven `mvn clean compile robovm:create-ipa robovm:iphone-sim`

* Run with Gradle `gradle clean build createIPA launchIPhoneSimulator`
	* tested with v2.2.1
	* the initial build takes around 2min, all subsequent builds are substantially faster

# References
* [RoboVM Project](http://www.robovm.com)
* [RoboVM Samples](https://github.com/robovm/robovm-samples)
* [JavaFX on iOS & Android](http://javafxports.org)

* [Tired of being stuck with Java 7 syntax on Android? Target Android with Kotlin!](http://kotlinlang.org)