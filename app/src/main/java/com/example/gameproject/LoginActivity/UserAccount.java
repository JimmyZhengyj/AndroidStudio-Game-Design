package com.example.gameproject.LoginActivity;


public class UserAccount {
  String username;
  String password;


  public UserAccount(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  //    public void saveUser() {
  //        PrintWriter out = null;
  //        try {
  //            OutputStream outStream = openFileOutput
  //            FileOutputStream out = new FileOutputStream("/data/User/"+username+".ser");
  //            ObjectOutputStream objectOut = new ObjectOutputStream(out);
  //            objectOut.writeObject(this);
  //            objectOut.close();
  //            out.close();
  //        } catch (Exception i) {
  //            i.printStackTrace();
  //        }
  //    }

  //    public UserAccount loadUser(String username) {
  ////        File file = new File("data/User");
  //        try {
  //            FileInputStream in = new FileInputStream("/data/User/"+username+".ser");
  //            ObjectInputStream objectIn = new ObjectInputStream(in);
  //            return  (UserAccount) objectIn.readObject();
  //
  //        } catch (Exception i) {
  //            i.printStackTrace();
  //        }
  //        return null;
  //
  //    }

}
