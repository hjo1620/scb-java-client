package org.dannil.scbapi.api;

import java.util.Locale;

public abstract class AbstractAPI {

	protected Locale locale;

	protected void setLocale(Locale locale) {
		this.locale = locale;
	}

}
