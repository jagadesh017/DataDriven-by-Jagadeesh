package base;

import java.io.IOException;

public interface DriverActions {

    public void browserLaunch();
    public void closeBrowser();
    public void navigateTo() throws IOException;

}
