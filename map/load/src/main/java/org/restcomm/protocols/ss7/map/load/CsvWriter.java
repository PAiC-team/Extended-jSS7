package org.restcomm.protocols.ss7.map.load;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CsvWriter {

    private final String name;

    private Map<String, Counter> counters = new LinkedHashMap<String, Counter>();

    private ScheduledExecutorService printExecutor = Executors.newScheduledThreadPool(1);

    public CsvWriter(String name) {
        this.name = name;
    }

    public void start(int initialDelay, int writerThreadPeriod) throws FileNotFoundException, UnsupportedEncodingException {
        CsvTask csvTask = new CsvTask(this);
        this.printExecutor.scheduleAtFixedRate(csvTask, initialDelay, writerThreadPeriod, TimeUnit.MILLISECONDS);
    }

    public void stop(int terminationDelay) throws InterruptedException {
        Thread.sleep(terminationDelay);
        this.printExecutor.shutdown();
        boolean isTerminated = this.printExecutor.awaitTermination(terminationDelay, TimeUnit.MILLISECONDS);
        if (!isTerminated)
            this.printExecutor.shutdownNow();
    }

    public void addCounter(String counterName) {
        Counter counter = new Counter(counterName);
        this.counters.put(counterName, counter);
    }

    public void incrementCounter(String counterName) {
        Counter counter = this.counters.get(counterName);
        if (counter != null) {
            counter.incrementAndGet();
        }
    }

    public String name() {
        return this.name;
    }

    public List<Counter> getCounters() {
        return new ArrayList<Counter>(this.counters.values());
    }

}
