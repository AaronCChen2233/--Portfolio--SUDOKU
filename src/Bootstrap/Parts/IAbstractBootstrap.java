package Bootstrap.Parts;

import Bootstrap.App.EApp;
import Bootstrap.Tools.Alog;

import java.util.ArrayList;
import java.util.Hashtable;

public interface IAbstractBootstrap {
    Hashtable<String, String> argumentAndParamaters = new Hashtable<String, String>();

    default boolean OnAppStateChange(EApp eAppOldState, EApp eAppNewState) {
        Alog.logInfo(this.getClass().getCanonicalName() + "is going from " + eAppOldState + " to " + eAppNewState);
        return true;
    }

    void setAppState(EApp eAppNewState);

    EApp getAppState();

    default void InitializeApplication() {
        Alog.logInfo(this.getClass().getName() + " is about to be Initialized");
        this.setAppState(EApp.Initialized);
        this.OnInitialized();
    }

    void OnInitialized();

    default void ShutdownApplication() {
        Alog.logShutdown(this.getClass().getName() + " is able to be Shutdown");
        this.setAppState(EApp.Shutdown);
        this.OnShutdown();
    }

    void OnShutdown();

    default void OnAppException(Exception e) {
        Alog.logException(this.getClass().getCanonicalName() + " exception!");
        Alog.logException("Details: " + e);
    }

    default void OnAppException(Exception e, Object fromObject) {
        Alog.logException(this.getClass().getCanonicalName() + " exception triggered by " + fromObject);
        Alog.logException("\t" + fromObject.toString() + " - Details: " + e);
    }

    default void OnAppErrorOrWarning(String message) {
        Alog.logWarningWithDate("Warning: " + message);
    }

    /*Called in applications main loop*/
    void OnApplicationUpdate();
}
