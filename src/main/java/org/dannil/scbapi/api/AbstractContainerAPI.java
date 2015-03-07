package org.dannil.scbapi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractContainerAPI extends AbstractAPI {

	protected List<AbstractAPI> apis;

	protected AbstractContainerAPI() {
		this.apis = new ArrayList<AbstractAPI>();
	}

	@Override
	public final void setLocale(Locale locale) {
		System.out.println(locale.getLanguage());
		super.locale = locale;

		for (AbstractAPI api : this.apis) {
			api.setLocale(super.locale);
		}
	}

}