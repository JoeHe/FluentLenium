package org.fluentlenium.core.conditions;

import org.fluentlenium.core.FluentDriver;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebElement;

public class AbstractFluentListConditionsTest {
    @Mock
    protected WebElement webElement1;

    @Mock
    protected WebElement webElement2;

    @Mock
    protected WebElement webElement3;

    @Mock
    protected FluentDriver fluentDriver;

    protected FluentWebElement fluentWebElement1;

    protected FluentWebElement fluentWebElement2;

    protected FluentWebElement fluentWebElement3;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        fluentWebElement1 = new FluentWebElement(webElement1);
        fluentWebElement2 = new FluentWebElement(webElement2);
        fluentWebElement3 = new FluentWebElement(webElement3);
    }
}
