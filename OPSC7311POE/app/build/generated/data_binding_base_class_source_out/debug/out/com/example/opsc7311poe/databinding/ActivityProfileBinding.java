// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final View empyViews;

  @NonNull
  public final ImageView imgPhotoUp;

  @NonNull
  public final ImageView imgWhiteBackground;

  @NonNull
  public final ImageView ivGoogle;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final TextView tvCustomiseProfile;

  @NonNull
  public final TextView tvEditusername;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvFullName;

  @NonNull
  public final TextView tvFullNameInput;

  @NonNull
  public final TextView tvNewPhoto;

  @NonNull
  public final TextView tvProfilePhoto;

  @NonNull
  public final TextView tvSeePassword;

  @NonNull
  public final TextView tvuserProfile;

  private ActivityProfileBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnSave,
      @NonNull View empyViews, @NonNull ImageView imgPhotoUp, @NonNull ImageView imgWhiteBackground,
      @NonNull ImageView ivGoogle, @NonNull ConstraintLayout main, @NonNull TextView textView10,
      @NonNull TextView textView11, @NonNull TextView textView9,
      @NonNull TextView tvCustomiseProfile, @NonNull TextView tvEditusername,
      @NonNull TextView tvEmail, @NonNull TextView tvFullName, @NonNull TextView tvFullNameInput,
      @NonNull TextView tvNewPhoto, @NonNull TextView tvProfilePhoto,
      @NonNull TextView tvSeePassword, @NonNull TextView tvuserProfile) {
    this.rootView = rootView;
    this.btnSave = btnSave;
    this.empyViews = empyViews;
    this.imgPhotoUp = imgPhotoUp;
    this.imgWhiteBackground = imgWhiteBackground;
    this.ivGoogle = ivGoogle;
    this.main = main;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView9 = textView9;
    this.tvCustomiseProfile = tvCustomiseProfile;
    this.tvEditusername = tvEditusername;
    this.tvEmail = tvEmail;
    this.tvFullName = tvFullName;
    this.tvFullNameInput = tvFullNameInput;
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
      id = R.id.btnSave;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.empyViews;
      View empyViews = ViewBindings.findChildViewById(rootView, id);
      if (empyViews == null) {
        break missingId;
      }

      id = R.id.img_photoUp;
      ImageView imgPhotoUp = ViewBindings.findChildViewById(rootView, id);
      if (imgPhotoUp == null) {
        break missingId;
      }

      id = R.id.img_whiteBackground;
      ImageView imgWhiteBackground = ViewBindings.findChildViewById(rootView, id);
      if (imgWhiteBackground == null) {
        break missingId;
      }

      id = R.id.iv_Google;
      ImageView ivGoogle = ViewBindings.findChildViewById(rootView, id);
      if (ivGoogle == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

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

      id = R.id.tvFullNameInput;
      TextView tvFullNameInput = ViewBindings.findChildViewById(rootView, id);
      if (tvFullNameInput == null) {
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

      return new ActivityProfileBinding((ConstraintLayout) rootView, btnSave, empyViews, imgPhotoUp,
          imgWhiteBackground, ivGoogle, main, textView10, textView11, textView9, tvCustomiseProfile,
          tvEditusername, tvEmail, tvFullName, tvFullNameInput, tvNewPhoto, tvProfilePhoto,
          tvSeePassword, tvuserProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}