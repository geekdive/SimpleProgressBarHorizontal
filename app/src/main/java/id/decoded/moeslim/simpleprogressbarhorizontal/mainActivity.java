package id.decoded.moeslim.simpleprogressbarhorizontal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class mainActivity extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Get the widgets reference from XML layout
        final TextView tv = (TextView) findViewById(R.id.tv);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);

        progressStatus = 0;

        /* A Thread is a concurrent unit of execution. It has its own call stack for
           methods being invoked, their arguments and local variables. Each application
           has at least one thread running when it is started, the main thread,
           in the main ThreadGroup. The runtime keeps its own threads
           in the system thread group. */

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    // Update the progress status
                    progressStatus += 1;
                    // Try to sleep the thread for 20 milliseconds
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            // Show the progress on TextView
                            tv.setText(progressStatus + "%");
                            // If progressStatuss to 100 then
                            System.out.println("Hasil Progress Status: " + progressStatus + "%");
                            int hasil = progressStatus;
                            if(hasil == 100){
                                Intent i = new Intent(mainActivity.this, secondActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation



    }
}
