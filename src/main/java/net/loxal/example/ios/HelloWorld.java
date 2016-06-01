/*
 * Copyright 2016 Alexander Orlov <alexander.orlov@loxal.net>
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

import com.bugvm.apple.foundation.NSAutoreleasePool;
import com.bugvm.apple.uikit.UIApplication;
import com.bugvm.apple.uikit.UIApplicationDelegateAdapter;
import com.bugvm.apple.uikit.UIApplicationLaunchOptions;
import com.bugvm.apple.uikit.UIScreen;
import com.bugvm.apple.uikit.UIWindow;
import net.loxal.example.ios.view.RootViewController;

public class HelloWorld extends UIApplicationDelegateAdapter {
	public static void main(final String... args) {
		final NSAutoreleasePool autoreleasePool = new NSAutoreleasePool();
		UIApplication.main(args, null, HelloWorld.class);
		autoreleasePool.close();
	}

	@Override
	public boolean didFinishLaunching(final UIApplication app, final UIApplicationLaunchOptions launchOptions) {
		final UIWindow window = new UIWindow(UIScreen.getMainScreen().getNativeBounds());
		window.setRootViewController(new RootViewController());
		window.makeKeyAndVisible();

		return true;
	}
}