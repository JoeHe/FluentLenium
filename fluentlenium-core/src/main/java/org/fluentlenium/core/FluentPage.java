package org.fluentlenium.core;

import lombok.experimental.Delegate;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.page.PageAnnotations;
import org.openqa.selenium.By;

/**
 * Use the Page Object Pattern to have more resilient tests.
 */
public abstract class FluentPage implements FluentPageControl {

    private PageAnnotations pageAnnotations = new PageAnnotations(getClass());

    protected FluentPage() {
    }

    protected FluentPage(FluentControl control) {
        initPage(control);
    }

    private FluentControl control;

    @Delegate
    private FluentControl getFluentControl() {
        return control;
    }

    public void initPage(FluentControl control) {
        this.control = control;
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
        By by = pageAnnotations.buildBy();
        findFirst(by);
    }

    @Override
    public final void go() {
        goTo(getUrl());
    }
}
