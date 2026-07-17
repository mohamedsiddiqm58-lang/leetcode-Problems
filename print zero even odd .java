class ZeroEvenOdd {
    private int n;
    private int current = 1;
    private int turn = 0; // 0 = zero, 1 = odd, 2 = even

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x"
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (current <= n) {
            while (turn != 0) {
                wait();
            }

            if (current > n) break;

            printNumber.accept(0);

            if (current % 2 == 1)
                turn = 1;
            else
                turn = 2;

            notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (current <= n) {
            while (turn != 1) {
                wait();
                if (current > n) return;
            }

            printNumber.accept(current);
            current++;
            turn = 0;
            notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (current <= n) {
            while (turn != 2) {
                wait();
                if (current > n) return;
            }

            printNumber.accept(current);
            current++;
            turn = 0;
            notifyAll();
        }
    }
}
