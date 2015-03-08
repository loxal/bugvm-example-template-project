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
import net.loxal.example.kotlin.ios.model.Quote
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
import java.io.IOException
import java.net.URI
import net.loxal.user.ios.App

public class RootViewController : UIViewController() {
    private val v = getView()
    private val quoteContainer = UITextView()
    private val nextQuote = UIButton.create(UIButtonType.System)
    private val mapper = ObjectMapper()

    private val httpClient = DefaultHttpClient()
    private val uri: URI = URI.create("http://rest-kit-test-v1.test.cf.hybris.com/dilbert-quote/manager")
    private val httpGet: HttpGet = HttpGet(uri);

    {
        v.setBackgroundColor(UIColor.white())

        initNextQuoteUi()
        initQuoteContainer()
    }

    private fun initQuoteContainer() {
        quoteContainer.setFrame(CGRect(5.0, 100.0, 310.0, 100.0))
        quoteContainer.setText(INIT_QUOTE)

        v.addSubview(quoteContainer)
    }

    private fun initNextQuoteUi() {
        nextQuote.setFrame(CGRect(115.0, 260.0, 90.0, 30.0))
        nextQuote.setTitle("Next Quote", UIControlState.Normal)
        v.addSubview(nextQuote)

        nextQuote.addOnTouchUpInsideListener({ control, event ->
            val managerQuote = fetchManagerQuote();
            showQuote(managerQuote);
        })
    }

    private fun showQuote(managerQuote: String) {
        try {
            val quote = mapper.readValue<Quote>(managerQuote, javaClass<Quote>())
            quoteContainer.setText("“" + quote.quote + "” \n\n\n \t\t\t\t\t\t – Dilbert, the Manager")
        } catch (e: IOException) {
            App.LOG.severe(e.getMessage())
        }
    }

    private fun fetchManagerQuote(): String {
        try {
            ByteArrayOutputStream().use { out ->
                val response = httpClient.execute(httpGet)
                val statusLine = response.getStatusLine()
                val entity = response.getEntity()
                if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                    entity.writeTo(out)
                    return out.toString()
                } else {
                    entity.getContent().close()
                }
            }
        } catch (e: IOException) {
            App.LOG.severe(e.getMessage())
        }

        return INIT_QUOTE
    }

    class object {
        private val INIT_QUOTE = "Locally it works."
    }
}
