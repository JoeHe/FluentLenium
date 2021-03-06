package org.fluentlenium.core.navigation;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public interface NavigationControl {

    /**
     * Open the page, using the url defined in the page
     *
     * @param page page to open
     * @param <P>  Type of FluentPage
     * @return Opened page.
     * @see FluentPage#getUrl()
     */
    <P extends FluentPage> P goTo(P page);

    /**
     * Open the url page
     *
     * @param url page URL to visit
     */
    void goTo(String url);

    /**
     * Open the url page in a new tab
     *
     * @param url the url of the page to
     */
    void goToInNewTab(String url);

    /**
     * Switch to the selected Element (if element is null or not an iframe, or haven't an id then
     * switch to the default)
     *
     * @param element fluent web element
     */
    void switchTo(FluentWebElement element);

    /**
     * Switch to the default element
     */
    void switchTo();

    /**
     * Switch to the default element
     */
    void switchToDefault();

    /**
     * Maximize browser window
     */
    void maximizeWindow();

    /**
     * Return the source of the page
     *
     * @return source of the page under test
     */
    String pageSource();

    /**
     * Return the title of the page
     *
     * @return browser window title
     */
    String title();


    /**
     * return the cookies as a set
     *
     * @return set of cookies
     */
    Set<Cookie> getCookies();

    /**
     * return the corresponding cookie given a name
     *
     * @param name cookie name
     * @return cookie selected by name
     */
    Cookie getCookie(String name);

    /**
     * Return the url of the page. If a base url is provided, the current url will be relative to that base url.
     *
     * @return current URL
     */
    String url();

    /**
     * Return the raw Selenium WebDriver
     *
     * @return selenium webdriver
     */
    WebDriver getDriver();
}
