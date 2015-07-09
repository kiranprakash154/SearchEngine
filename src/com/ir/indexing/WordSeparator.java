package com.ir.indexing;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WordSeparator {
	private static final String BUNDLE_NAME = "com.ir.indexing.separator"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private WordSeparator() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
