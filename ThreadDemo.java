// ═══════════════════════════════════════════════════════════════════
//  ThreadDemo.java
//  Demonstrates printing 1–100 using:
//    PART A – Extending Thread class
//      1. Without threading (sequential)
//      2. With threading (concurrent)
//    PART B – Implementing Runnable interface
//      3. Without threading (sequential)
//      4. With threading (concurrent)
// ═══════════════════════════════════════════════════════════════════

// ───────────────────────────────────────────────────────────────────
//  PART A ▸ Using Thread class (extends Thread)
// ───────────────────────────────────────────────────────────────────

// Class 1 – prints 1 to 33
class Range1 extends Thread {
    @Override
    public void run() {
        System.out.println("\n[Thread-1] START → printing 1 to 33");
        for (int i = 1; i <= 33; i++) {
            System.out.println("  Thread-1 → " + i);
        }
        System.out.println("[Thread-1] END");
    }
}

// Class 2 – prints 34 to 66
class Range2 extends Thread {
    @Override
    public void run() {
        System.out.println("\n[Thread-2] START → printing 34 to 66");
        for (int i = 34; i <= 66; i++) {
            System.out.println("  Thread-2 → " + i);
        }
        System.out.println("[Thread-2] END");
    }
}

// Class 3 – prints 67 to 100
class Range3 extends Thread {
    @Override
    public void run() {
        System.out.println("\n[Thread-3] START → printing 67 to 100");
        for (int i = 67; i <= 100; i++) {
            System.out.println("  Thread-3 → " + i);
        }
        System.out.println("[Thread-3] END");
    }
}

// ───────────────────────────────────────────────────────────────────
//  PART B ▸ Using Runnable interface (implements Runnable)
// ───────────────────────────────────────────────────────────────────

// Runnable Class 1 – prints 1 to 33
class RunnableRange1 implements Runnable {
    @Override
    public void run() {
        System.out.println("\n[Runnable-1] START → printing 1 to 33");
        for (int i = 1; i <= 33; i++) {
            System.out.println("  Runnable-1 → " + i);
        }
        System.out.println("[Runnable-1] END");
    }
}

// Runnable Class 2 – prints 34 to 66
class RunnableRange2 implements Runnable {
    @Override
    public void run() {
        System.out.println("\n[Runnable-2] START → printing 34 to 66");
        for (int i = 34; i <= 66; i++) {
            System.out.println("  Runnable-2 → " + i);
        }
        System.out.println("[Runnable-2] END");
    }
}

// Runnable Class 3 – prints 67 to 100
class RunnableRange3 implements Runnable {
    @Override
    public void run() {
        System.out.println("\n[Runnable-3] START → printing 67 to 100");
        for (int i = 67; i <= 100; i++) {
            System.out.println("  Runnable-3 → " + i);
        }
        System.out.println("[Runnable-3] END");
    }
}

// ───────────────────────────────────────────────────────────────────
//  MAIN CLASS
// ───────────────────────────────────────────────────────────────────
public class ThreadDemo {

    // ── separator utility ──
    static void separator(String title) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  " + title);
        System.out.println("═".repeat(60));
    }

    public static void main(String[] args) throws InterruptedException {

        // ════════════════════════════════════════════════════════
        //  PART A-1 ▸ WITHOUT THREADING (extends Thread)
        //  run() called directly → sequential, ordered output
        // ════════════════════════════════════════════════════════
        separator("PART A-1 : WITHOUT THREADING (extends Thread)");
        System.out.println("Calling run() directly — NO new threads created.");
        System.out.println("Output will be perfectly sequential: 1 → 100\n");

        Range1 r1 = new Range1();
        Range2 r2 = new Range2();
        Range3 r3 = new Range3();

        r1.run();   // executes in main thread — sequential
        r2.run();
        r3.run();

        System.out.println("\n[ANALYSIS A-1] Output is ordered 1-100 because");
        System.out.println("  run() is a normal method call — no concurrency.");


        // ════════════════════════════════════════════════════════
        //  PART A-2 ▸ WITH THREADING (extends Thread)
        //  start() called → 3 concurrent threads, interleaved output
        // ════════════════════════════════════════════════════════
        separator("PART A-2 : WITH THREADING (extends Thread)");
        System.out.println("Calling start() — 3 NEW threads launched concurrently.");
        System.out.println("Output will be INTERLEAVED / unpredictable!\n");

        Range1 t1 = new Range1();
        Range2 t2 = new Range2();
        Range3 t3 = new Range3();

        t1.start();   // launches Thread-1 concurrently
        t2.start();   // launches Thread-2 concurrently
        t3.start();   // launches Thread-3 concurrently

        // wait for all threads to finish before continuing
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\n[ANALYSIS A-2] Output was interleaved because");
        System.out.println("  all 3 threads ran concurrently (CPU schedules them).");
        System.out.println("  Order is non-deterministic — changes every run.");


        // ════════════════════════════════════════════════════════
        //  PART B-1 ▸ WITHOUT THREADING (implements Runnable)
        //  run() called directly on Runnable object → sequential
        // ════════════════════════════════════════════════════════
        separator("PART B-1 : WITHOUT THREADING (implements Runnable)");
        System.out.println("Calling run() directly on Runnable — sequential.\n");

        RunnableRange1 rr1 = new RunnableRange1();
        RunnableRange2 rr2 = new RunnableRange2();
        RunnableRange3 rr3 = new RunnableRange3();

        rr1.run();   // just a method call — no new thread
        rr2.run();
        rr3.run();

        System.out.println("\n[ANALYSIS B-1] Same as A-1 — sequential 1-100.");
        System.out.println("  Runnable.run() called directly = no threading.");


        // ════════════════════════════════════════════════════════
        //  PART B-2 ▸ WITH THREADING (implements Runnable)
        //  Wrap Runnable in Thread → start() launches threads
        // ════════════════════════════════════════════════════════
        separator("PART B-2 : WITH THREADING (implements Runnable)");
        System.out.println("Wrapping Runnable in Thread and calling start().");
        System.out.println("Output will be INTERLEAVED — concurrent execution!\n");

        Thread rt1 = new Thread(new RunnableRange1());
        Thread rt2 = new Thread(new RunnableRange2());
        Thread rt3 = new Thread(new RunnableRange3());

        rt1.start();
        rt2.start();
        rt3.start();

        rt1.join();
        rt2.join();
        rt3.join();

        System.out.println("\n[ANALYSIS B-2] Same interleaving as A-2.");
        System.out.println("  Runnable interface is preferred over extending Thread");
        System.out.println("  because Java supports only single inheritance —");
        System.out.println("  Runnable lets your class extend another class too.");


        // ════════════════════════════════════════════════════════
        //  FINAL SUMMARY
        // ════════════════════════════════════════════════════════
        separator("FINAL SUMMARY");
        System.out.println("""
          ┌───────────────┬──────────────┬──────────────────────────────┐
          │  Approach     │  Method Call │  Output                      │
          ├───────────────┼──────────────┼──────────────────────────────┤
          │ A-1 Thread    │  run()       │ Sequential   1 → 100         │
          │ A-2 Thread    │  start()     │ Interleaved  (random order)  │
          │ B-1 Runnable  │  run()       │ Sequential   1 → 100         │
          │ B-2 Runnable  │  start()     │ Interleaved  (random order)  │
          └───────────────┴──────────────┴──────────────────────────────┘

          KEY RULES:
          1. run()   = normal method call → runs in CURRENT thread → sequential
          2. start() = creates NEW thread → runs CONCURRENTLY → interleaved
          3. Runnable is preferred over Thread inheritance (flexible design)
          4. join()  = makes main thread WAIT until that thread finishes
        """);
    }
}
