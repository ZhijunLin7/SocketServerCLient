
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
                long elapsedTime=(System.currentTimeMillis()-c.getLastTimeMessage());
                if(elapsedTime>=healthTimeOut){
                    if (status=="ok") {
                        c.sendPing();
                        this.status="write";
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
