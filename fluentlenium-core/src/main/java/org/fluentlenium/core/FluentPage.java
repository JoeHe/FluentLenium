package org.fluentlenium.core;

import org.fluentlenium.core.annotation.PageUrl;

/**
 * Use the Page Object Pattern to have more resilient tests.
 */
public abstract class FluentPage extends DefaultFluentContainer implements FluentPageControl, FluentContainer {

    protected FluentPage() {
        super();
    }

    protected FluentPage(FluentControl control) {
        super(control);
    }

    @Override
    public String getUrl() {
        if (this.getClass().isAnnotationPresent(PageUrl.class)) {
            String url = this.getClass().getAnnotation(PageUrl.class).value();
            if (!url.isEmpty()) {
                return url;
            }
        }
        return null;
    }

    @Override
    public void isAt() {
    }

    @Override
    public final void go() {
        goTo(getUrl());
    }
}
