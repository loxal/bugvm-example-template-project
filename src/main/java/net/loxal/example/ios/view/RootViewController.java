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

import com.fasterxml.jackson.databind.ObjectMapper;
import net.loxal.example.ios.HelloWorld;
import net.loxal.example.kotlin.ios.model.Quote;
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
    private static final String INIT_QUOTE = "Locally it works.";
    private final UIView view = getView();
    private final UILabel quoteContainer = new UILabel();
    private final UIButton nextQuote = UIButton.create(UIButtonType.System);
    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient httpClient = new DefaultHttpClient();
    private final URI uri = URI.create("http://rest-kit-test-v1.test.cf.hybris.com/dilbert-quote/manager");
    private final HttpGet httpGet = new HttpGet(uri);

    {
        view.setBackgroundColor(UIColor.white());

        initNextQuoteUi();
        initQuoteContainer();
    }

    public RootViewController() {
    }

    private void initQuoteContainer() {
        quoteContainer.setFrame(new CGRect(10.0, 100, 300, 100));
        quoteContainer.setText(INIT_QUOTE);
        quoteContainer.setHighlightedTextColor(UIColor.blue());
        quoteContainer.setFont(UIFont.getSystemFont(12.0));

        view.addSubview(quoteContainer);
    }

    private void initNextQuoteUi() {
        nextQuote.setFrame(new CGRect(115, 260, 90, 30));
        nextQuote.setTitle("Next Quote", UIControlState.Normal);
        view.addSubview(nextQuote);

        nextQuote.addOnTouchUpInsideListener((control, event) -> {
            final String managerQuote = fetchManagerQuote();
            try {
                final Quote quote = mapper.readValue(managerQuote, Quote.class);
                quoteContainer.setText(quote.getQuote());
            } catch (final IOException e) {
                HelloWorld.LOG.severe(e.getMessage());
            }
        });
    }

    private String fetchManagerQuote() {
        try {
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
            HelloWorld.LOG.severe(e.getMessage());
        }
        return INIT_QUOTE;
    }
}
