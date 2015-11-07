/*
 * Copyright 2015 Alexander Orlov <alexander.orlov@loxal.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.loxal.example.kotlin.ios

import net.loxal.example.kotlin.ios.view.RootViewController
import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.*

public class HelloWorld : UIApplicationDelegateAdapter() {

    override fun didFinishLaunching(app: UIApplication?, launchOptions: UIApplicationLaunchOptions?): Boolean {
        val window = UIWindow(UIScreen.getMainScreen().nativeBounds)
        window.rootViewController = RootViewController()
        window.makeKeyAndVisible()

        return true
    }

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            val autoreleasePool = NSAutoreleasePool()
            UIApplication.main<UIApplication, HelloWorld>(args, null, HelloWorld::class.java)
            autoreleasePool.close()
        }
    }
}