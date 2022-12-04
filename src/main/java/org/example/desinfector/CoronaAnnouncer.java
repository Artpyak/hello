package org.example.desinfector;

public class CoronaAnnouncer implements Announcer {
    @InjectByTy
    private Recommendator recommendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
