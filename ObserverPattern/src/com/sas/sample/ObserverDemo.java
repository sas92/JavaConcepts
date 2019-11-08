package com.sas.sample;

import java.util.*;

/**
 * Reference: https://www.baeldung.com/java-observer-pattern
 */

class NewsAgency extends Observable {
    private String news;

    public void setNews(String news) {
        this.news = news;
        setChanged();
        notifyObservers(news);
    }
}

class Channel implements Observer {
    private List<String> newsList = new ArrayList<>();

    @Override
    public void update(Observable o, Object news) {
        newsList.add((String) news);
        System.out.println("\nNews: " + newsList);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "newsList=" + newsList +
                '}';
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency observable = new NewsAgency();
        Channel observer = new Channel();
        observable.addObserver(observer);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nType news to broadcast or type 'Exit' to exit: ");
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit"))
                return;
            observable.setNews(message);
        }
    }
}
