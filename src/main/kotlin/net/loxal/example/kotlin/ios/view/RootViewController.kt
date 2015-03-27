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

package net.loxal.example.kotlin.ios.view

import org.robovm.apple.coregraphics.CGRect
import org.robovm.apple.uikit.*

class RootViewController : UIViewController() {
    private var clickCount: Int = 0

    init {
        val view = getView()
        view.setBackgroundColor(UIColor.white())

        val button = UIButton.create(UIButtonType.System)
        button.setFrame(CGRect(60.0, 100.0, 200.0, 30.0))
        button.setTitle("“Hello World” in Kotlin!", UIControlState.Normal)

        button.addOnTouchUpInsideListener(object : UIControl.OnTouchUpInsideListener {
            override fun onTouchUpInside(control: UIControl, event: UIEvent) {
                button.setTitle("Touch #" + ++clickCount, UIControlState.Normal)
            }
        })

        view.addSubview(button)
    }
}
