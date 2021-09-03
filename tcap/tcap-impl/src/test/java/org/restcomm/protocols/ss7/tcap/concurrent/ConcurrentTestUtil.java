
package org.restcomm.protocols.ss7.tcap.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

/**
*
* @author jaime casero
*
*/
public class ConcurrentTestUtil {

    public static final List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<Throwable>());

    public static void assertConcurrent(final String message, final List<Runnable> runnables, final int maxTimeout) throws InterruptedException {
        exceptions.clear();

        final int numThreads = runnables.size();
        final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        try {
            final CountDownLatch allExecutorThreadsReady = new CountDownLatch(numThreads);
            final CountDownLatch afterInitBlocker = new CountDownLatch(1);
            final CountDownLatch allDone = new CountDownLatch(numThreads);
            for (final Runnable submittedTestRunnable : runnables) {
                threadPool.submit(new Runnable() {
                    public void run() {
                        allExecutorThreadsReady.countDown();
                        try {
                            afterInitBlocker.await();
                            submittedTestRunnable.run();
                        } catch (final Throwable e) {
                            exceptions.add(e);
                            e.printStackTrace();
                        } finally {
                            allDone.countDown();
                        }
                    }
                });
            }
            // wait until all threads are ready
            Assert.assertTrue(
                    "Timeout initializing threads! Perform long lasting initializations before passing runnables to assertConcurrent",
                    allExecutorThreadsReady.await(runnables.size() * 10, TimeUnit.MILLISECONDS));
            // start all test runners
            afterInitBlocker.countDown();
            Assert.assertTrue(message + " timeout! More than" + maxTimeout + " millis",
                    allDone.await(maxTimeout, TimeUnit.MILLISECONDS));
        } finally {
            threadPool.shutdownNow();
        }
        Assert.assertTrue(message + "failed with exception(s)" + exceptions, exceptions.isEmpty());
    }

}
