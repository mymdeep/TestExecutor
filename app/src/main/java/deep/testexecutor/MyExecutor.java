package deep.testexecutor;

import java.util.concurrent.Executor;

/**
 * Created by wangfei on 17/1/24.
 */
public class MyExecutor implements Executor{
    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
