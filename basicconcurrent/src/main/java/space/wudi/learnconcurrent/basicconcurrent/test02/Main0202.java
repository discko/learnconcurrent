package space.wudi.learnconcurrent.basicconcurrent.test02;

/**
 * use volatile to prevent instruction reorder
 */
@SuppressWarnings("all")
public class Main0202 {

    private static /*volatile*/ Main0202 instance = null;
    private int a;
    private Main0202(int n){
        a = n;
    }

    public static Main0202 getInstance(int n){
        // double check null
        if(instance == null){
            synchronized (Main0202.class){
                if(instance == null){
                    instance = new Main0202(n);
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        final int COUNT = 100;
        for(int i=0;i<COUNT;i++){
            final int n = i;
            new Thread(()->{
                System.out.println(Main0202.getInstance(n).a);
            }).start();
        }
    }
}
