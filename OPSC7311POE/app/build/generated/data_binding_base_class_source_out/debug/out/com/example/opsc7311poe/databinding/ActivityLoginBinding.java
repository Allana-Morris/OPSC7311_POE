// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.opsc7311poe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnGoogleSignin;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView ivPassword;

  @NonNull
  public final ImageView ivProfPhoto;

  @NonNull
  public final ImageView ivUsername;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView tvNoAccount;

  @NonNull
  public final TextView tvOpenRegister;

  @NonNull
  public final EditText tvPassword;

  @NonNull
  public final TextView tvResetPassword;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final EditText tvUsername;

  @NonNull
  public final View view;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnGoogleSignin,
      @NonNull Button btnLogin, @NonNull ImageView imageView, @NonNull ImageView ivPassword,
      @NonNull ImageView ivProfPhoto, @NonNull ImageView ivUsername, @NonNull ConstraintLayout main,
      @NonNull TextView tvNoAccount, @NonNull TextView tvOpenRegister, @NonNull EditText tvPassword,
      @NonNull TextView tvResetPassword, @NonNull TextView tvTitle, @NonNull EditText tvUsername,
      @NonNull View view) {
    this.rootView = rootView;
    this.btnGoogleSignin = btnGoogleSignin;
    this.btnLogin = btnLogin;
    this.imageView = imageView;
    this.ivPassword = ivPassword;
    this.ivProfPhoto = ivProfPhoto;
    this.ivUsername = ivUsername;
    this.main = main;
    this.tvNoAccount = tvNoAccount;
    this.tvOpenRegister = tvOpenRegister;
    this.tvPassword = tvPassword;
    this.tvResetPassword = tvResetPassword;
    this.tvTitle = tvTitle;
    this.tvUsername = tvUsername;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_Google_signin;
      Button btnGoogleSignin = ViewBindings.findChildViewById(rootView, id);
      if (btnGoogleSignin == null) {
        break missingId;
      }

      id = R.id.btn_Login;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.iv_Password;
      ImageView ivPassword = ViewBindings.findChildViewById(rootView, id);
      if (ivPassword == null) {
        break missingId;
      }

      id = R.id.iv_ProfPhoto;
      ImageView ivProfPhoto = ViewBindings.findChildViewById(rootView, id);
      if (ivProfPhoto == null) {
        break missingId;
      }

      id = R.id.iv_Username;
      ImageView ivUsername = ViewBindings.findChildViewById(rootView, id);
      if (ivUsername == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.tv_NoAccount;
      TextView tvNoAccount = ViewBindings.findChildViewById(rootView, id);
      if (tvNoAccount == null) {
        break missingId;
      }

      id = R.id.tv_Open_Register;
      TextView tvOpenRegister = ViewBindings.findChildViewById(rootView, id);
      if (tvOpenRegister == null) {
        break missingId;
      }

      id = R.id.tv_Password;
      EditText tvPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvPassword == null) {
        break missingId;
      }

      id = R.id.tv_ResetPassword;
      TextView tvResetPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvResetPassword == null) {
        break missingId;
      }

      id = R.id.tv_Title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.tv_Username;
      EditText tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnGoogleSignin, btnLogin,
          imageView, ivPassword, ivProfPhoto, ivUsername, main, tvNoAccount, tvOpenRegister,
          tvPassword, tvResetPassword, tvTitle, tvUsername, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
