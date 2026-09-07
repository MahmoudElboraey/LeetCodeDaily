class FooBar {
    private int n;

    private boolean printFooo = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) { // n is a shared state but , threads will read the value it only not modifiying it 
        synchronized(this){
          
                while(!printFooo) {
                    this.wait();
                }

                printFoo.run();
                printFooo = false;
                this.notifyAll();
        }
        
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
        synchronized(this){
           
                while (printFooo) {
                    this.wait();
                }
                printBar.run();
                printFooo = true;
                this.notifyAll();
            
        }
        	
        }
    }
}