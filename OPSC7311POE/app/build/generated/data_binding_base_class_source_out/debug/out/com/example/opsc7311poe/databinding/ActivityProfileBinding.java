// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.opsc7311poe.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNav;

  @NonNull
  public final Button btnSaveProfile;

  @NonNull
  public final EditText edtEmailEdit;

  @NonNull
  public final EditText edtFullNameEdit;

  @NonNull
  public final EditText edtPasswordEdit;

  @NonNull
  public final EditText edtUserNameEdit;

  @NonNull
  public final View empyViews;

  @NonNull
  public final ImageButton ibtnPhoto;

  @NonNull
  public final ImageView imgWhiteBackground;

  @NonNull
  public final ImageView ivProfPhoto;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView tvCustomiseProfile;

  @NonNull
  public final TextView tvEditusername;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvFullName;

  @NonNull
  public final TextView tvNewPhoto;

  @NonNull
  public final TextView tvProfilePhoto;

  @NonNull
  public final TextView tvSeePassword;

  @NonNull
  public final TextView tvuserProfile;

  private ActivityProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomNav, @NonNull Button btnSaveProfile,
      @NonNull EditText edtEmailEdit, @NonNull EditText edtFullNameEdit,
      @NonNull EditText edtPasswordEdit, @NonNull EditText edtUserNameEdit, @NonNull View empyViews,
      @NonNull ImageButton ibtnPhoto, @NonNull ImageView imgWhiteBackground,
      @NonNull ImageView ivProfPhoto, @NonNull ConstraintLayout main,
      @NonNull TextView tvCustomiseProfile, @NonNull TextView tvEditusername,
      @NonNull TextView tvEmail, @NonNull TextView tvFullName, @NonNull TextView tvNewPhoto,
      @NonNull TextView tvProfilePhoto, @NonNull TextView tvSeePassword,
      @NonNull TextView tvuserProfile) {
    this.rootView = rootView;
    this.bottomNav = bottomNav;
    this.btnSaveProfile = btnSaveProfile;
    this.edtEmailEdit = edtEmailEdit;
    this.edtFullNameEdit = edtFullNameEdit;
    this.edtPasswordEdit = edtPasswordEdit;
    this.edtUserNameEdit = edtUserNameEdit;
    this.empyViews = empyViews;
    this.ibtnPhoto = ibtnPhoto;
    this.imgWhiteBackground = imgWhiteBackground;
    this.ivProfPhoto = ivProfPhoto;
    this.main = main;
    this.tvCustomiseProfile = tvCustomiseProfile;
    this.tvEditusername = tvEditusername;
    this.tvEmail = tvEmail;
    this.tvFullName = tvFullName;
    this.tvNewPhoto = tvNewPhoto;
    this.tvProfilePhoto = tvProfilePhoto;
    this.tvSeePassword = tvSeePassword;
    this.tvuserProfile = tvuserProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNav;
      BottomNavigationView bottomNav = ViewBindings.findChildViewById(rootView, id);
      if (bottomNav == null) {
        break missingId;
      }

      id = R.id.btnSaveProfile;
      Button btnSaveProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnSaveProfile == null) {
        break missingId;
      }

      id = R.id.edtEmailEdit;
      EditText edtEmailEdit = ViewBindings.findChildViewById(rootView, id);
      if (edtEmailEdit == null) {
        break missingId;
      }

      id = R.id.edtFullNameEdit;
      EditText edtFullNameEdit = ViewBindings.findChildViewById(rootView, id);
      if (edtFullNameEdit == null) {
        break missingId;
      }

      id = R.id.edtPasswordEdit;
      EditText edtPasswordEdit = ViewBindings.findChildViewById(rootView, id);
      if (edtPasswordEdit == null) {
        break missingId;
      }

      id = R.id.edtUserNameEdit;
      EditText edtUserNameEdit = ViewBindings.findChildViewById(rootView, id);
      if (edtUserNameEdit == null) {
        break missingId;
      }

      id = R.id.empyViews;
      View empyViews = ViewBindings.findChildViewById(rootView, id);
      if (empyViews == null) {
        break missingId;
      }

      id = R.id.ibtnPhoto;
      ImageButton ibtnPhoto = ViewBindings.findChildViewById(rootView, id);
      if (ibtnPhoto == null) {
        break missingId;
      }

      id = R.id.img_whiteBackground;
      ImageView imgWhiteBackground = ViewBindings.findChildViewById(rootView, id);
      if (imgWhiteBackground == null) {
        break missingId;
      }

      id = R.id.iv_ProfPhoto;
      ImageView ivProfPhoto = ViewBindings.findChildViewById(rootView, id);
      if (ivProfPhoto == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.tvCustomiseProfile;
      TextView tvCustomiseProfile = ViewBindings.findChildViewById(rootView, id);
      if (tvCustomiseProfile == null) {
        break missingId;
      }

      id = R.id.tvEditusername;
      TextView tvEditusername = ViewBindings.findChildViewById(rootView, id);
      if (tvEditusername == null) {
        break missingId;
      }

      id = R.id.tvEmail;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tvFullName;
      TextView tvFullName = ViewBindings.findChildViewById(rootView, id);
      if (tvFullName == null) {
        break missingId;
      }

      id = R.id.tvNewPhoto;
      TextView tvNewPhoto = ViewBindings.findChildViewById(rootView, id);
      if (tvNewPhoto == null) {
        break missingId;
      }

      id = R.id.tvProfilePhoto;
      TextView tvProfilePhoto = ViewBindings.findChildViewById(rootView, id);
      if (tvProfilePhoto == null) {
        break missingId;
      }

      id = R.id.tvSeePassword;
      TextView tvSeePassword = ViewBindings.findChildViewById(rootView, id);
      if (tvSeePassword == null) {
        break missingId;
      }

      id = R.id.tvuserProfile;
      TextView tvuserProfile = ViewBindings.findChildViewById(rootView, id);
      if (tvuserProfile == null) {
        break missingId;
      }

      return new ActivityProfileBinding((ConstraintLayout) rootView, bottomNav, btnSaveProfile,
          edtEmailEdit, edtFullNameEdit, edtPasswordEdit, edtUserNameEdit, empyViews, ibtnPhoto,
          imgWhiteBackground, ivProfPhoto, main, tvCustomiseProfile, tvEditusername, tvEmail,
          tvFullName, tvNewPhoto, tvProfilePhoto, tvSeePassword, tvuserProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
