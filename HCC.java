


public class HCC implements Runnable{
    
    private Conection c;
    private int healthTimeOut=1000;
    private String status; 

    public HCC(Conection c){
        this.c=c;
        this.status="ok";
    }

    @Override
    public void run() {
        while (true) {
            if (c.isOk()) {
                System.out.println("pasado 2");
                int elapsedTime=(int) (System.currentTimeMillis()-c.getLastTimeMessage());
                System.out.println(elapsedTime);
                if(elapsedTime>=healthTimeOut){
                    System.out.println("pasado 3");
                    if (status=="ok") {
                        c.sendPing();
                        this.status="write";
                        System.out.println("pasado 4");
                    }else{
                        System.out.println("pasado 4 close");
                        c.killSocket();
                        this.status="ok";
                    }
                }else{
                    this.status="ok";
                }
            }
            try {
                Thread.sleep(this.healthTimeOut/5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



}
