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
import org.robovm.apple.uikit.UIButton
import org.robovm.apple.uikit.UIButtonType
import org.robovm.apple.uikit.UIColor
import org.robovm.apple.uikit.UIControl
import org.robovm.apple.uikit.UIControlState
import org.robovm.apple.uikit.UIEvent
import org.robovm.apple.uikit.UIViewController
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import java.net.URI
import org.robovm.apple.uikit.UILabel
import org.robovm.apple.uikit.UITextView

class RootViewController : UIViewController() {
    {
        getView().setBackgroundColor(UIColor.white())
        getView().addSubview(createHelloWorldButton())
    }
    val t = UITextView.UITextViewPtr()
    val l = UILabel.UILabelPtr()
    val httpClient = DefaultHttpClient()

    fun fetchManagerQuote(): String {
        //        Logger.getGlobal().info("hello")
        val response = httpClient.execute(HttpGet(URI.create("http://rest-kit-test-v1.test.cf.hybris.com/dilbert-quote/manager")))
        //        val statusLine = response.getStatusLine()
        //        Logger.getGlobal().info("ere")
        //        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
        //            val out = ByteArrayOutputStream()
        //            Logger.getGlobal().info("ere1")
        //            response.getEntity().writeTo(out)
        //            val responseContent = out.toString()
        //            out.close()
        //            Logger.getGlobal().info("ere2")
        //            Logger.getGlobal().info(responseContent)
        //            Logger.getGlobal().info("ere22")
        //            return responseContent
        return "blahaaaaa"
        //        } else {
        //            Logger.getGlobal().info("bad")
        //            response.getEntity().getContent().close()
        //            return "Locally it works!"
        //        }
    }

    fun createHelloWorldButton(): UIButton {
        val button = UIButton.create(UIButtonType.System)

        button.setFrame(CGRect(60.0, 100.0, 200.0, 30.0))
        button.setTitle("Next Quote", UIControlState.Normal)

        val label = UILabel()
        label.setFrame(CGRect(10.0, 240.0, 300.0, 100.0))
        label.setText("This never happend before.")
        label.setTintColor(UIColor.blue())
        getView().addSubview(label)

        button.addOnTouchUpInsideListener(object : UIControl.OnTouchUpInsideListener {
            override fun onTouchUpInside(control: UIControl, event: UIEvent) {
                val managerQuote = fetchManagerQuote()
                //                button.setTitle("${managerQuote}", UIControlState.Normal)
                label.setText("${managerQuote}")
            }
        })

        return button
    }
}
