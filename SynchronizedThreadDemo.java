// ═══════════════════════════════════════════════════════════════════
//  SynchronizedThreadDemo.java
//
//  Demonstrates SYNCHRONIZED output of 3 threads using join()
//  so the final output is always in order: 1 → 100
//
//  APPROACH:
//    Thread-1 prints  1–33   → main waits via join()
//    Thread-2 prints 34–66   → main waits via join()
//    Thread-3 prints 67–100  → main waits via join()
//
//  Both approaches shown:
//    PART A – extends Thread
//    PART B – implements Runnable
// ═══════════════════════════════════════════════════════════════════


// ───────────────────────────────────────────────────────────────────
//  PART A ▸ Using extends Thread  +  join()
// ───────────────────────────────────────────────────────────────────

class PrintThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("  [Thread-1] Started  → printing 1 to 33");
        for (int i = 1; i <= 33; i++) {
            System.out.println("    Thread-1 → " + i);
        }
        System.out.println("  [Thread-1] Finished ✓");
    }
}

class PrintThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("  [Thread-2] Started  → printing 34 to 66");
        for (int i = 34; i <= 66; i++) {
            System.out.println("    Thread-2 → " + i);
        }
        System.out.println("  [Thread-2] Finished ✓");
    }
}

class PrintThread3 extends Thread {
    @Override
    public void run() {
        System.out.println("  [Thread-3] Started  → printing 67 to 100");
        for (int i = 67; i <= 100; i++) {
            System.out.println("    Thread-3 → " + i);
        }
        System.out.println("  [Thread-3] Finished ✓");
    }
}


// ───────────────────────────────────────────────────────────────────
//  PART B ▸ Using implements Runnable  +  join()
// ───────────────────────────────────────────────────────────────────

class RunnablePrint1 implements Runnable {
    @Override
    public void run() {
        System.out.println("  [Runnable-1] Started  → printing 1 to 33");
        for (int i = 1; i <= 33; i++) {
            System.out.println("    Runnable-1 → " + i);
        }
        System.out.println("  [Runnable-1] Finished ✓");
    }
}

class RunnablePrint2 implements Runnable {
    @Override
    public void run() {
        System.out.println("  [Runnable-2] Started  → printing 34 to 66");
        for (int i = 34; i <= 66; i++) {
            System.out.println("    Runnable-2 → " + i);
        }
        System.out.println("  [Runnable-2] Finished ✓");
    }
}

class RunnablePrint3 implements Runnable {
    @Override
    public void run() {
        System.out.println("  [Runnable-3] Started  → printing 67 to 100");
        for (int i = 67; i <= 100; i++) {
            System.out.println("    Runnable-3 → " + i);
        }
        System.out.println("  [Runnable-3] Finished ✓");
    }
}


// ───────────────────────────────────────────────────────────────────
//  MAIN CLASS
// ───────────────────────────────────────────────────────────────────
public class SynchronizedThreadDemo {

    static void line() {
        System.out.println("─".repeat(55));
    }

    public static void main(String[] args) throws InterruptedException {

        // ════════════════════════════════════════════════════════
        //  PART A ▸ extends Thread  +  join()
        // ════════════════════════════════════════════════════════
        System.out.println("═".repeat(55));
        System.out.println("  PART A : extends Thread  +  join()");
        System.out.println("═".repeat(55));
        System.out.println("  Output will be SYNCHRONISED: 1 → 100 in order\n");

        PrintThread1 t1 = new PrintThread1();
        PrintThread2 t2 = new PrintThread2();
        PrintThread3 t3 = new PrintThread3();

        // ── Start Thread-1, then WAIT for it to fully finish ──
        t1.start();
        t1.join();   // main thread PAUSES here until Thread-1 is done
        line();

        // ── Start Thread-2, then WAIT for it to fully finish ──
        t2.start();
        t2.join();   // main thread PAUSES here until Thread-2 is done
        line();

        // ── Start Thread-3, then WAIT for it to fully finish ──
        t3.start();
        t3.join();   // main thread PAUSES here until Thread-3 is done
        line();

        System.out.println("\n  [ANALYSIS A]");
        System.out.println("  join() forced the main thread to WAIT after each");
        System.out.println("  start(). So Thread-2 could not begin until Thread-1");
        System.out.println("  fully completed. Output is always ordered 1 → 100.");


        // ════════════════════════════════════════════════════════
        //  PART B ▸ implements Runnable  +  join()
        // ════════════════════════════════════════════════════════
        System.out.println("\n" + "═".repeat(55));
        System.out.println("  PART B : implements Runnable  +  join()");
        System.out.println("═".repeat(55));
        System.out.println("  Output will be SYNCHRONISED: 1 → 100 in order\n");

        Thread rt1 = new Thread(new RunnablePrint1());
        Thread rt2 = new Thread(new RunnablePrint2());
        Thread rt3 = new Thread(new RunnablePrint3());

        // ── Start Runnable-1, WAIT for it ──
        rt1.start();
        rt1.join();   // blocks main until Runnable-1 finishes
        line();

        // ── Start Runnable-2, WAIT for it ──
        rt2.start();
        rt2.join();   // blocks main until Runnable-2 finishes
        line();

        // ── Start Runnable-3, WAIT for it ──
        rt3.start();
        rt3.join();   // blocks main until Runnable-3 finishes
        line();

        System.out.println("\n  [ANALYSIS B]");
        System.out.println("  Same synchronisation achieved via Runnable + join().");
        System.out.println("  Runnable is the preferred approach in Java because");
        System.out.println("  it doesn't consume the single inheritance slot.");


        // ════════════════════════════════════════════════════════
        //  FINAL SUMMARY
        // ════════════════════════════════════════════════════════
        System.out.println("\n" + "═".repeat(55));
        System.out.println("  HOW join() SYNCHRONISES THE OUTPUT");
        System.out.println("═".repeat(55));
        System.out.println("""
          TIMELINE WITH join():
          ─────────────────────────────────────────────────────
          main  ──► start(T1) ──► join(T1) ──► WAIT...
          T1             ├─── runs 1-33 ───────┤
          main                                 └──► start(T2) ──► join(T2) ──► WAIT...
          T2                                              ├─── runs 34-66 ───────┤
          main                                                                   └──► start(T3) ──► join(T3)
          T3                                                                                ├─── runs 67-100 ──┤
          ─────────────────────────────────────────────────────

          WITHOUT join():
            All 3 threads run at the same time → output is random

          WITH join():
            Thread-2 cannot start until Thread-1 is done
            Thread-3 cannot start until Thread-2 is done
            → Output is ALWAYS in order: 1 → 100
        """);
    }
}
