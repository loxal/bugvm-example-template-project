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

package net.loxal.example.ios.view;

import net.loxal.example.ios.HelloWorld;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class RootViewController extends UIViewController {
    private final UIView view = getView();
    private final UILabel quotationContainer = new UILabel();
    private final UIButton nextQuote = UIButton.create(UIButtonType.System);

    {
        view.setBackgroundColor(UIColor.white());

        initNextQuoteUi();
        initQuotationContainer();
    }

    RootViewController() {
    }

    private void initQuotationContainer() {
        quotationContainer.setFrame(new CGRect(10.0, 100, 300, 100));
        quotationContainer.setText("This never happened before.");
        quotationContainer.setHighlightedTextColor(UIColor.blue());
        quotationContainer.setFont(UIFont.getSystemFont(12.0));

        view.addSubview(quotationContainer);
    }

    private void initNextQuoteUi() {
        nextQuote.setFrame(new CGRect(115, 260, 90, 30));
        nextQuote.setTitle("Next Quote", UIControlState.Normal);
        view.addSubview(nextQuote);

        nextQuote.addOnTouchUpInsideListener((control, event) -> {
            final String managerQuote = fetchManagerQuote();
            quotationContainer.setText(managerQuote);

//            val mapper = ObjectMapper()
//            val restCode = mapper.readValue(restCodeData, javaClass < RestCode > ())
        });
    }

    private String fetchManagerQuote() {
        try {
            final HttpClient httpClient = new DefaultHttpClient();
            final URI uri = URI.create("http://rest-kit-test-v1.test.cf.hybris.com/dilbert-quote/manager");
            final HttpGet httpGet = new HttpGet(uri);
            final HttpResponse response = httpClient.execute(httpGet);
            final StatusLine statusLine = response.getStatusLine();
            final HttpEntity entity = response.getEntity();
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                entity.writeTo(out);
                return out.toString();
            } else {
                entity.getContent().close();
            }
        } catch (final IOException e) {
            HelloWorld.LOG.info(e.getMessage());
        }
        return "Locally it works!";
    }
}
