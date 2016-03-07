**Support this project**
<!-- BADGES/ -->
<span class="badge-paypal">
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=MA847TR65D4N2" title="Donate to this project using PayPal">
<img src="https://img.shields.io/badge/paypal-donate-yellow.svg" alt="PayPal Donate"/>
</a></span>
<span class="badge-flattr">
<a href="https://flattr.com/submit/auto?fid=o6ok7n&url=https%3A%2F%2Fgithub.com%2Floxal" title="Donate to this project using Flattr">
<img src="https://img.shields.io/badge/flattr-donate-yellow.svg" alt="Flattr Donate" />
</a></span>
<span class="badge-gratipay"><a href="https://gratipay.com/~loxal" title="Donate weekly to this project using Gratipay">
<img src="https://img.shields.io/badge/gratipay-donate-yellow.svg" alt="Gratipay Donate" />
</a></span>
<!-- /BADGES -->
# RoboVM Template Project
An out of the box working *RoboVM* template project for *IntelliJ IDEA*, **Maven**, and **Gradle** in order to build iOS apps.
Currently it’s not possible to start the application from IntelliJ IDEA.

## Getting Started
* Prerequisites
	* Xcode
	* Java JDK 8

* The initial build takes around 2min.
	All subsequent builds are substantially faster.

* Run with Maven 
	`mvn clean compile robovm:iphone-sim`
	
	* Run a “Hello, World!” app written in ***Kotlin***
	
	`mvn clean compile robovm:iphone-sim -D robovm.mainClass=net.loxal.example.kotlin.ios.HelloWorld`

	<img src="hello-world-ios-robovm-kotlin.png" alt="“Hello, World!” in Kotlin" title="“Hello, World!” in Kotlin"
	width="150" height="276">


* Run with Gradle `gradle clean build launchIPhoneSimulator`
	* tested with v2.3

## References

* [RoboVM Project](http://www.robovm.com)
* [RoboVM Samples](https://github.com/robovm/robovm-samples)
* [JavaFX on iOS & Android](http://javafxports.org)
* [Tired of being stuck with Java 7 syntax on Android? Target Android with Kotlin!](http://kotlinlang.org)
