class Foo {

    public Foo() {
        
    }

    private int count = 0;

    public void first(Runnable printFirst) throws InterruptedException {
        
        synchronized(this){
        while(count != 0){
            this.wait();
        }
        printFirst.run();
        count++;
        this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        
        synchronized(this){
            while (count != 1){
                this.wait();
            }
            printSecond.run();
            count++;
            this.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        synchronized(this){
            while(count != 2) this.wait();

            printThird.run();
            count++;
            this.notifyAll();
        }
    }
}