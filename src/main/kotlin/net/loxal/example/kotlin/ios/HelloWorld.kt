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

public class HelloWorld : UIApplicationDelegateAdapter() {
    private var clickCount: Int = 0

    override fun didFinishLaunching(app: UIApplication?, launchOptions: UIApplicationLaunchOptions?): Boolean {
        val button = UIButton.create(UIButtonType.System)
        button.setFrame(CGRect(100.0, 100.0, 90.0, 30.0))
        button.setTitle("Kotlin :)", UIControlState.None)

        button.addOnTouchUpInsideListener(object : UIControl.OnTouchUpInsideListener {
            override fun onTouchUpInside(control: UIControl, event: UIEvent) {
                button.setTitle("Touch #" + ++clickCount, UIControlState.None)
            }
        })

        val window = UIWindow(UIScreen.getMainScreen().getNativeBounds())
        window.setBackgroundColor(UIColor.white())
        window.addSubview(button)
        window.makeKeyAndVisible()

        return true
    }

    class object {
        platformStatic fun main(vararg args: String) {
            val autoreleasePool = NSAutoreleasePool()
            UIApplication.main<UIApplication, HelloWorld>(args, null, javaClass<HelloWorld>())
            autoreleasePool.close()
        }
    }
}

//fun main(vararg args: String) = HelloWorld.main(*args)