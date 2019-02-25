package com.example.nitin.singuplogin;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
  SharedPreferences perfs;
  SharedPreferences.Editor editor;
    private Context ctx;
  public Session(Context ctx){
      this.ctx=ctx;
      perfs=ctx.getSharedPreferences("myApp",Context.MODE_PRIVATE);
      editor=perfs.edit();
      }
      public void setLoggedin(boolean loggedin){
      editor.putBoolean("LoggedInmode",false);
      editor.commit();
      }
      public boolean loggedin()
      {
          return perfs.getBoolean("LoggedInmmode",false);
      }
}
