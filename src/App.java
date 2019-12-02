import Bootstrap.App.EApp;
import Bootstrap.Parts.AbstractBootstrap;
import Bootstrap.Parts.EArgument;
import Bootstrap.Tools.Alog;
import MVVM.Parts.Model.SUDOKUGeneraterModel;
import MVVM.Parts.View.SUDOKUWindow;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class App extends AbstractBootstrap {
    static App bootstrapTest;
    public App(String[] CommandLineArguments) {
        super(CommandLineArguments);
    }

    public static void main(String[] args) {
        Alog.logStartup("Started App Main");
        bootstrapTest = new App(args);
    }

    @Override
    public void OnInitialized() {
        /*Now just build a scaffolding*/
        if (argumentAndParamaters.size() > 0) {
            argumentAndParamaters.forEach((k, v) -> {
                EArgument eArgument = null;
                try {
                    k = k.replace("-", "");
                    k = k.toLowerCase();
                    eArgument = EArgument.valueOf(k);
                } catch (Exception ex) {

                }

                if (eArgument != null) {
                    switch (eArgument) {
                        case run:
                            if (Integer.parseInt(v) > 5 || Integer.parseInt(v) < 1) {
                                showRunHelper();
                                break;
                            }
                            SUDOKUGeneraterModel.generate(Integer.parseInt(v));
                            break;
                        case runwindow:
                            /*window version coming soon*/
                            SUDOKUWindow sudokuWindow = new SUDOKUWindow();
                            break;
                    }
                }
            });
        } else {
            /*No Argument*/
            showRunHelper();
        }
    }

    @Override
    public void OnShutdown() {

    }

    @Override
    public void OnApplicationUpdate() {
//        setAppState(EApp.ShuttingDown);
    }
}