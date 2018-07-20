package pornthida.th.ac.srru.myofficer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by DELL on 17/7/2561.
 */

public class MyAlert {

    //Explicit
    private Context context;
    private String titleString, messageString;

    public MyAlert(Context context, String titleString, String messageString) {
        this.context = context;
        this.titleString = titleString;
        this.messageString = messageString;
    }

    public void myDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setIcon(R.drawable.k);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

}   //Main Class
