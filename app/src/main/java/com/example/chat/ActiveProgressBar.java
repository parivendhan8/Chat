package com.example.chat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

public class ActiveProgressBar {

    public static Dialog progressBar;

    public static void  show(Context context)
    {
        progressBar  = new Dialog(context);
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressBar.setContentView(R.layout.progress_bar);
        progressBar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressBar.setCanceledOnTouchOutside(false);
        progressBar.setCancelable(false);
        progressBar.show();
    }
    public static void dismiss()
    {
        if (progressBar != null)
        {
            progressBar.dismiss();
        }
    }
}
