package Bootstrap.Parts;

import Bootstrap.Tools.Alog;

public class RunArgumentHelper implements IArgumentHelper {
    @Override
    public void showArgumentHelper() {
        Alog.logWarning("Argument not found or Argument format not correct");
        Alog.logWarning("Run SUDOKUGenerater please type -run [difficulty(1~5)]");
        Alog.logWarning("EX: -run 2");
        Alog.logWarning("1 to 5 is easy to difficult");
    }
}
