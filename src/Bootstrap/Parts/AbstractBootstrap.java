package Bootstrap.Parts;

import Bootstrap.App.EApp;
import Bootstrap.Tools.Alog;
import Bootstrap.Tools.GetConfigProperty;
import MVVM.Parts.Model.SUDOKUGeneraterModel;

import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractBootstrap implements IAbstractBootstrap {
    private EApp _appState = EApp.StateUnknown;
    private boolean threwException = false;
    private boolean threwErrors = false;
    private int errorCount = 0;
    private int exceptionCount = 0;

    public AbstractBootstrap() {

    }

    public AbstractBootstrap(String[] CommandLineArguments) {
        Alog.logInfo("Abstract Bootstrap Constructor Called: appState: " + getAppState());
        if (getAppState() != EApp.StateUnknown) {
            threwErrors = true;
            errorCount++;
            Alog.logError("We are not in State Unknown, have ou tried to initialize already?");
            this.OnAppErrorOrWarning(this.getClass().getName() + " is in an unexpected state ");
        } else if (getAppState() == EApp.StateUnknown) {
            Alog.logInfo("Setting state to Initializing");
            setAppState(EApp.Initializing);
        } else {
            Alog.logUnknown("We should never see this message! - check your logic for this set of if statements");
            threwErrors = true;
            errorCount++;
            setAppState(EApp.Exception);
        }
        Alog.logInfo("I see: " + CommandLineArguments.length + " Command Line Arguments");

        /*put Argument and Paramater in argumentAndParamaters Hashtable*/
        ArrayList<String> commandLine = new ArrayList<String>();
        for (String s : CommandLineArguments) {
            commandLine.add(s);
        }
        commandLine.add("-");
        ArrayList<String> tempParamaters = new ArrayList<String>();
        String ThisArgument = "";
        for (String c : commandLine) {
            /*if commandLine have switch which mean it is a Argument if not is Paramater*/
            if (!Switchs.allownSwitch.contains(c.charAt(0))) {
                tempParamaters.add(c);
            } else {
                argumentAndParamaters.put(ThisArgument, String.join(",", tempParamaters));
                tempParamaters = new ArrayList<String>();
                ThisArgument = c;
            }
        }
        argumentAndParamaters.remove("");

        try {
            Alog.logInfo("Entering Try..catch - we should catch any 'Exceptions' if they happen");
            this.InitializeApplication();
        } catch (Exception anyException) {
            threwException = true;
            exceptionCount++;
            Alog.logException("We caught an exception...triggering our error handler");
            this.OnAppException(anyException, this);
        } finally {
            Alog.logInfo("Checking application state:" + getAppState());
            if (getAppState() == EApp.Initialized) {
                Alog.logInfo(this.getClass().getName() + " initialized successfully");
            } else {
                Alog.logError(this.getClass().getName() + " failed to initialize");
                // record the error happened
                threwErrors = true;
                errorCount++;
                // let dev decide what to do about it.
                this.OnAppErrorOrWarning(this.getClass().getName() + " failed to initialize");
            }
            Alog.logInfo("Leaving main try...catch...finally");
            Alog.logInfo("There were exceptions: " + threwException + " - " + exceptionCount);
            Alog.logInfo("There were errors    : " + threwErrors + " - " + errorCount);
        }

        if ((threwException == false) && (getAppState() == EApp.Initialized) && (threwErrors == false)) {
            Alog.logInfo("No issues, setting state to isRunning");
            setAppState(EApp.IsRunning);
        } else if ((threwException == false) && (getAppState() == EApp.Initialized) && (threwErrors == true)) {
            Alog.logWarning("Some issues, but will continue to setting state to isRunning");
            setAppState(EApp.IsRunning);
        } else if (threwException == true) {
            setAppState(EApp.Exception);
            this.threwErrors = true;
            this.errorCount++;
            Alog.logWarning("\tThere are now errors    : " + threwErrors + " - " + errorCount);
            this.OnAppErrorOrWarning("Inconsistent application state reached, likely, logic broken!");
        }

        boolean isShuttingDown = false;
        try {
            Alog.logInfo("Entering Try..catch - we should catch any 'Exceptions' if they happen");
            while ((getAppState() == EApp.IsRunning)) {
                this.OnApplicationUpdate();
            }

            switch (getAppState()) {
                case ShuttingDown: {
                    isShuttingDown = true;
                    Alog.logShutdown("Shutting down normally");
                    /*let the try cry..catch.. 'fall through' and we'll continue in the 'finally'*/
                    break;
                }
                default: {
                    Alog.logException("Application appears to be terminating due to an unexpected state: " + getAppState());

                    errorCount++;
                    threwErrors = true;
                    this.OnAppErrorOrWarning("Application appears to be terminating due to an unexpected state: " + getAppState());
                }
            }
        } catch (Exception e) {
            exceptionCount++;
            threwException = true;
            this.OnAppException(e);
        } finally {
            if ((isShuttingDown == true || getAppState().equals(EApp.ShuttingDown)) && ((errorCount == 0) && (exceptionCount == 0))) { /*everything are good, shutting down normally, no errors, no exceptions*/
                setAppState(EApp.Shutdown);
                Alog.logInfo(this.getClass().getName() + " shutdown normally");
                /*call our Shutdown event*/
                this.ShutdownApplication();
                System.exit(0);
            } else {/*shutting down for some other reason*/
                Alog.logWarning(this.getClass().getName() + " shutdown abnormally");
                setAppState(EApp.Exception);
                /*exit with a non zero status (abnormal)*/
                /*call our Shutdown event*/
                this.ShutdownApplication();
                System.exit(getAppState().hashCode());
            }
        }

        Alog.logInfo("Errors:" + threwErrors + " - " + errorCount);
        Alog.logInfo("Exceptions:" + threwException + " - " + exceptionCount);
    }

    @Override
    public void setAppState(EApp eAppNewState) {
        if (this.OnAppStateChange(getAppState(), eAppNewState)) {
            this._appState = eAppNewState;
        } else {
            Alog.logWarning("State change from" + getAppState() + " to " + eAppNewState + " blocked!");
        }
    }

    @Override
    public EApp getAppState() {
        return _appState;
    }

    protected void showRunHelper() {
        RunArgumentHelper runArgumentHelper = new RunArgumentHelper();
        runArgumentHelper.showArgumentHelper();
    }
}
