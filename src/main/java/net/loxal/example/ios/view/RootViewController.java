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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;

import java.io.IOException;
import java.net.URI;

public class RootViewController extends UIViewController {
    final HttpClient httpClient = new DefaultHttpClient();
    final URI uri = URI.create("http://rest-kit-test-v1.test.cf.hybris.com/dilbert-quote/manager");
    private int clickCount;
    public RootViewController() {
        final UIView view = getView();
        view.setBackgroundColor(UIColor.white());

        final UIButton button = UIButton.create(UIButtonType.System);
        button.setFrame(new CGRect(100, 100, 90, 30));
        button.setTitle("Touch :)", UIControlState.Normal);

        final UILabel label = new UILabel();
        label.setFrame(new CGRect(10.0, 240.0, 300.0, 100.0));
        label.setText("This never happened before?!");
        label.setBackgroundColor(UIColor.blue());
        view.addSubview(label);

        button.addOnTouchUpInsideListener((control, event) -> {
            button.setTitle("Touch #" + ++clickCount, UIControlState.Normal);
            label.setText("Touch #" + ++clickCount);
            fetchManagerQuote();
        });

        view.addSubview(button);
    }

    public void fetchManagerQuote() {
//        Logger.getGlobal().info("hello");
        try {
            final HttpResponse response = httpClient.execute(new HttpGet(uri));
//        val statusLine = response.getStatusLine()
//        Logger.getGlobal().info("ere")
//            return "blahaaaaa!";
        } catch (IOException e) {
//            e.printStackTrace();
        }
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
//        } else {
//            Logger.getGlobal().info("bad")
//            response.getEntity().getContent().close()
//        return "Locally it works!";
//        }
    }
}
