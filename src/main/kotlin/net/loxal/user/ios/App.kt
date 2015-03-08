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

package net.loxal.user.ios

import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication
import org.robovm.apple.uikit.UIApplicationDelegateAdapter
import org.robovm.apple.uikit.UIApplicationLaunchOptions
import org.robovm.apple.uikit.UIScreen
import org.robovm.apple.uikit.UIWindow
import kotlin.platform.platformStatic
import net.loxal.example.kotlin.ios.view.RootViewController
import java.util.logging.Logger

public class App : UIApplicationDelegateAdapter() {

    override fun didFinishLaunching(app: UIApplication?, launchOptions: UIApplicationLaunchOptions?): Boolean {
        val window = UIWindow(UIScreen.getMainScreen().getNativeBounds())
        window.setRootViewController(RootViewController())
        window.makeKeyAndVisible()

        return true
    }

    class object {
        val LOG: Logger = Logger.getGlobal()
        platformStatic fun main(vararg args: String) {
            val autoreleasePool = NSAutoreleasePool()
            UIApplication.main<UIApplication, App>(args, null, javaClass<App>())
            autoreleasePool.close()
        }
    }
}