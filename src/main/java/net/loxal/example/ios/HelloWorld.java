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

package net.loxal.example.ios;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;

public class HelloWorld extends UIApplicationDelegateAdapter {
	private int clickCount;

	public static void main(final String... args) {
		final NSAutoreleasePool autoreleasePool = new NSAutoreleasePool();
		UIApplication.main(args, null, HelloWorld.class);
		autoreleasePool.close();
	}

	@Override
	public boolean didFinishLaunching(final UIApplication app, final UIApplicationLaunchOptions launchOptions) {
		final UIButton button = UIButton.create(UIButtonType.System);
		button.setFrame(new CGRect(100, 100, 90, 30));
		button.setTitle("Touch :)", UIControlState.None);

		button.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
			@Override
			public void onTouchUpInside(UIControl control, UIEvent event) {
				button.setTitle("Touch #" + ++clickCount, UIControlState.None);
			}
		});

		final UIWindow window = new UIWindow(UIScreen.getMainScreen().getNativeBounds());
		window.setBackgroundColor(UIColor.white());
		window.addSubview(button);
		window.makeKeyAndVisible();

		return true;
	}
}