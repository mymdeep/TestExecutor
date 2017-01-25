package deep.testexecutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.myexecutor:
                myEcecutor();
                break;
            case R.id.threadpool:
                threadPoolEcecutor();
                break;
            case R.id.fix:
                fixEcecutor();
                break;
            case R.id.single:
                singleEcecutor();
                break;
            case R.id.cache:
                cacheEcecutor();
                break;
            case R.id.scheduled:
                scheduledEcecutor();
                break;
        }
    }
    private void myEcecutor(){
        MyExecutor executor = new MyExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"0s");
                try {
                    Thread.sleep(1000);
                    Log.e(TAG,"1s");
                    Thread.sleep(1000);
                    Log.e(TAG,"2s");
                    Thread.sleep(1000);
                    Log.e(TAG,"3s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    private void fixEcecutor(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,name+"0s");
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,name+"1s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"2s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"3s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    private void singleEcecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,name+"0s");
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,name+"1s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"2s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"3s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    private void cacheEcecutor(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,name+"0s");
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,name+"1s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"2s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"3s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
     */
    private void scheduledEcecutor(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
//            scheduledThreadPool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e(TAG,name+"0s");
//                    try {
//                        Thread.sleep(1000);
//                        Log.e(TAG,name+"1s");
//                        Thread.sleep(1000);
//                        Log.e(TAG,name+"2s");
//                        Thread.sleep(1000);
//                        Log.e(TAG,name+"3s");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, 4, TimeUnit.SECONDS);
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,name+"0s");
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,name+"1s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"2s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"3s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 2,4, TimeUnit.SECONDS);

        }
    }
    /**
     *线程池
     */
    private void threadPoolEcecutor(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 15, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0 ;i<15;i++){
            final String name = "第"+i+"个线程";
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,name+"0s");
                    try {
                        Thread.sleep(1000);
                        Log.e(TAG,name+"1s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"2s");
                        Thread.sleep(1000);
                        Log.e(TAG,name+"3s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
