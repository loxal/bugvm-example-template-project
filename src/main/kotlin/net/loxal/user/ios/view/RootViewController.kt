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

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.robovm.apple.coregraphics.CGRect
import org.robovm.apple.uikit.UIButton
import org.robovm.apple.uikit.UIButtonType
import org.robovm.apple.uikit.UIColor
import org.robovm.apple.uikit.UIControlState
import org.robovm.apple.uikit.UITextView
import org.robovm.apple.uikit.UIViewController

import java.io.ByteArrayOutputStream
import java.net.URI
import net.loxal.user.ios.model.Host
import org.robovm.apple.uikit.NSTextAlignment
import org.robovm.apple.uikit.UIControlContentHorizontalAlignment
import java.util.Date

public class RootViewController : UIViewController() {
    private val v = getView()
    private val infoContainer = UITextView()
    private val refresher = UIButton.create(UIButtonType.RoundedRect)
    private val mapper = ObjectMapper()

    private val httpClient = DefaultHttpClient()
    private val uri: URI = URI.create("http://rest-kit-test-v1.test.cf.hybris.com/who-am-i")
    private val httpGet: HttpGet = HttpGet(uri);

    {
        v.setBackgroundColor(UIColor.white())

        initRefreshUi()
        initInfoContainer()
        refreshStatus()
    }

    private fun initInfoContainer() {
        infoContainer.setFrame(CGRect(5.0, 100.0, 310.0, 100.0))
        infoContainer.setTextAlignment(NSTextAlignment.Center)

        v.addSubview(infoContainer)
    }

    private fun initRefreshUi() {
        refresher.setFrame(CGRect(5.0, 260.0, 310.0, 30.0))
        refresher.setTitle("Refresh Status", UIControlState.Normal)
        refresher.setContentHorizontalAlignment(UIControlContentHorizontalAlignment.Center)
        v.addSubview(refresher)

        refresher.addOnTouchUpInsideListener({ control, event -> refreshStatus() })
    }

    private fun refreshStatus() = showInfo(fetchHostInfo())

    private fun showInfo(info: String) {
        val host = mapper.readValue<Host>(info, javaClass<Host>())
        infoContainer.setText("Time: ${Date()} \n Host: ${host.name} | IP Address: ${host.address}")
    }

    private fun fetchHostInfo(): String {
        ByteArrayOutputStream().use { out ->
            val response = httpClient.execute(httpGet)
            val status = response.getStatusLine()
            val entity = response.getEntity()
            if (HttpStatus.SC_OK == status.getStatusCode()) {
                entity.writeTo(out)
                return out.toString()
            } else {
                entity.getContent().close()
            }
        }

        return "I’m very sorry, this should not happen."
    }
}
