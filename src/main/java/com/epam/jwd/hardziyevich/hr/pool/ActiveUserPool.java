package com.epam.jwd.hardziyevich.hr.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActiveUserPool {
    private static volatile ActiveUserPool instance = null;
    private final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    private ActiveUserPool() {

    }

    public static ActiveUserPool getInstance() {
        if (instance == null) {
            synchronized (ActiveUserPool.class) {
                if (instance == null) {
                    instance = new ActiveUserPool();
                }
            }
        }
        return instance;
    }

    public void add(String login) {
        blockingQueue.add(login);
    }

    public void remove(String login) {
        blockingQueue.remove(login);
    }

    public boolean isActive(String login) {
        return blockingQueue.contains(login);
    }
}
