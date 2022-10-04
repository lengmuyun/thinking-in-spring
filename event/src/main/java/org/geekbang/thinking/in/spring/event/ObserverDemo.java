package org.geekbang.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加观察者
        observable.addObserver(new EventObserver());
        // 发布事件
        observable.notifyObservers("Hello World");
    }

    static class EventObservable extends Observable {
        @Override
        public void notifyObservers() {
            setChanged();
            super.notifyObservers(new EventObject(new Object()));
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
        }

    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object arg) {
            EventObject event = (EventObject) arg;
            System.out.println("收到事件: " + event);
        }
    }

}
