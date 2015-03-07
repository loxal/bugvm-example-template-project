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

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class RootViewController extends UIViewController {
    private int clickCount;

    public RootViewController() {
        final UIView view = getView();
        view.setBackgroundColor(UIColor.white());

        final UIButton button = UIButton.create(UIButtonType.System);
        button.setFrame(new CGRect(100, 100, 90, 30));
        button.setTitle("Touch :)", UIControlState.Normal);

        button.addOnTouchUpInsideListener((control, event) ->
                        button.setTitle("Touch #" + ++clickCount, UIControlState.Normal)
        );


        Client c = ClientBuilder.newClient();

        c.target("http://example.com");


        // TODO take a look at rxjava-apache-http !!!!!!
//		https://github.com/ReactiveX/RxApacheHttp
//        try {
//            System.out.println("test");
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpResponse response = httpclient.execute(new HttpGet(URI.create("http://example.com")));
//            StatusLine statusLine = response.getStatusLine();
//            System.out.println("statusLine.getStatusCode() = " + statusLine.getStatusCode());
//            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                response.getEntity().writeTo(out);
//                String responseString = out.toString();
//                out.close();
////				button.setTitle(responseString, UIControlState.None);
////                Logger.getGlobal().warning(responseString);
////					System.out.println("responseString = " + responseString);
//                //..more logic
//            } else {
//                //Closes the connection.
//                System.out.println("123123 = " + 123123);
//                response.getEntity().getContent().close();
//                throw new IOException(statusLine.getReasonPhrase());
//            }
//        } catch (IOException e) {
//
//        }
        view.addSubview(button);
    }

}
