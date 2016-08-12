package org.fluentlenium.adapter;

import lombok.experimental.Delegate;
import org.fluentlenium.core.FluentDriver;
import org.fluentlenium.core.FluentDriverControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FluentAdapter implements FluentDriverControl, FluentDriverConfiguration {

    private final DriverContainer driverContainer;

    private String screenshotPath;

    private String htmlDumpPath;

    private TriggerMode screenshotMode;

    private TriggerMode htmlDumpMode;

    public FluentAdapter() {
        this(new DefaultDriverContainer());
    }

    public FluentAdapter(DriverContainer driverContainer) {
        this.driverContainer = driverContainer;
    }

    public FluentAdapter(WebDriver webDriver) {
        this(new DefaultDriverContainer(), webDriver);
    }

    public FluentAdapter(DriverContainer driverContainer, WebDriver webDriver) {
        this.driverContainer = driverContainer;
        initFluent(webDriver);
    }

    @Delegate(types = FluentDriverControl.class)
    private FluentDriver getFluentDriver() {
        return getDriverContainer().getFluentDriver();
    }

    boolean isFluentDriverAvailable() {
        return getDriverContainer().getFluentDriver() != null;
    }

    private void setFluentDriver(FluentDriver driver) {
        getDriverContainer().setFluentDriver(driver);
    }

    protected DriverContainer getDriverContainer() {
        return driverContainer;
    }

    /**
     * Load a {@link WebDriver} into this adapter.
     *
     * @param webDriver webDriver to use.
     * @return adapter
     * @throws IllegalStateException when trying to register a different webDriver that the current one.
     */
    public void initFluent(WebDriver webDriver) {
        if (webDriver == null) {
            releaseFluent();
            return;
        }

        if (getFluentDriver() != null) {
            if (getFluentDriver().getDriver() == webDriver) {
                return;
            }
            if (getFluentDriver().getDriver() != null) {
                throw new IllegalStateException(
                        "Trying to init a WebDriver, but another one is still running");
            }
        }

        FluentDriver fluentDriver = new FluentDriver(webDriver, this);
        setFluentDriver(fluentDriver);
        fluentDriver.inject(this);
    }

    /**
     * Release the current {@link WebDriver} from this adapter.
     */
    public void releaseFluent() {
        if (getFluentDriver() != null) {
            getFluentDriver().releaseFluent();
            setFluentDriver(null);
        }
    }

    /**
     * Override this method to change the driver
     *
     * @return returns WebDriver which is set to FirefoxDriver by default - can be overwritten
     */
    public WebDriver getDefaultDriver() {
        return new FirefoxDriver();
    }

    /**
     * Override this method to set the base URL to use when using relative URLs
     *
     * @return The base URL, or null if relative URLs should be passed to the driver untouched
     */
    public String getDefaultBaseUrl() {
        return null;
    }

    @Override
    public void setScreenshotPath(String path) {
        this.screenshotPath = path;
    }

    @Override
    public void setHtmlDumpPath(String htmlDumpPath) {
        this.htmlDumpPath = htmlDumpPath;
    }

    @Override
    public void setScreenshotMode(TriggerMode mode) {
        this.screenshotMode = mode;
    }

    @Override
    public TriggerMode getScreenshotMode() {
        return screenshotMode;
    }

    @Override
    public String getScreenshotPath() {
        return screenshotPath;
    }

    @Override
    public String getHtmlDumpPath() {
        return htmlDumpPath;
    }

    @Override
    public void setHtmlDumpMode(TriggerMode htmlDumpMode) {
        this.htmlDumpMode = htmlDumpMode;
    }

    @Override
    public TriggerMode getHtmlDumpMode() {
        return htmlDumpMode;
    }
}
