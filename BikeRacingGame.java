import java.util.*;

class EventManager {
    public static Biker[] rankedList = new Biker[10];

    public static void display() {
        System.out.println("\n--- Race Results ---");
        for (int i = 0; i < rankedList.length; i++) {
            if (rankedList[i] != null) {
                System.out.printf("Rank %d  :  %s  | StartTime: %d ms | EndTime: %d ms | Time Taken: %d ms\n",i + 1, rankedList[i].getName(), rankedList[i].getStartTime(), rankedList[i].getEndTime(), rankedList[i].getTimeTaken());
            }
        }
    }

    public static void countDown(int n) {
        System.out.println("The Race will start in:");
        for (int i = n; i >= 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Go!");
    }
}

class Biker implements Runnable {
    private String name;
    private long startTime;
    private long endTime;
    private int distance;
    private int speed;
    
    public Biker(String name, int dist, long startTime) {
        this.name = name;
        this.distance = dist;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }
    public long getStartTime(){
	return startTime;
    }
    public long getEndTime(){
	return endTime;
    }
    public long getTimeTaken() {
        return endTime - startTime;
    }
    public void run() {
        long delay = startTime - System.currentTimeMillis();
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < distance; i++) {
	    System.out.println(name);
            try {
		speed=(int) (Math.random() * 100 + 10);
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();
        synchronized (EventManager.rankedList) {
            for (int i = 0; i < EventManager.rankedList.length; i++) {
                if (EventManager.rankedList[i] == null) {
                    EventManager.rankedList[i] = this;
                    break;
                }
            }
        }
    }
}

class BikeRacingGame {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the distance for the race:");
        int distance = sc.nextInt();

        Thread[] TBikers = new Thread[10];
        long startTime = System.currentTimeMillis() + 10000; 
        EventManager.countDown(10);

        for (int i = 0; i < 10; i++) {
            TBikers[i] = new Thread(new Biker("Biker" + (i + 1), distance, startTime));
            TBikers[i].start();
        }

        for (int i = 0; i < 10; i++) {
            TBikers[i].join();
        }
        Arrays.sort(EventManager.rankedList, Comparator.comparingLong(Biker::getTimeTaken));
	
        System.out.println("Race Ended!");
        EventManager.display();
    }
}
