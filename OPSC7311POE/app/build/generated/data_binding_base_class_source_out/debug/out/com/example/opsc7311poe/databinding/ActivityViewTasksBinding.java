// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.opsc7311poe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityViewTasksBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout clNavBar;

  @NonNull
  public final FloatingActionButton createBtn;

  @NonNull
  public final FloatingActionButton createCatBtn;

  @NonNull
  public final ImageButton ibCalendar;

  @NonNull
  public final ImageButton ibHome;

  @NonNull
  public final ImageButton ibProfile;

  @NonNull
  public final ImageButton ibTimer;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ConstraintLayout linearLayout;

  @NonNull
  public final ConstraintLayout linearLayout2;

  @NonNull
  public final ConstraintLayout linearLayout4;

  @NonNull
  public final ConstraintLayout linearLayout5;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView tvCalendar;

  @NonNull
  public final TextView tvHome;

  @NonNull
  public final TextView tvProfile;

  @NonNull
  public final TextView tvTimer;

  @NonNull
  public final LinearLayout vertLayout;

  private ActivityViewTasksBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout clNavBar, @NonNull FloatingActionButton createBtn,
      @NonNull FloatingActionButton createCatBtn, @NonNull ImageButton ibCalendar,
      @NonNull ImageButton ibHome, @NonNull ImageButton ibProfile, @NonNull ImageButton ibTimer,
      @NonNull ImageView imageView, @NonNull ConstraintLayout linearLayout,
      @NonNull ConstraintLayout linearLayout2, @NonNull ConstraintLayout linearLayout4,
      @NonNull ConstraintLayout linearLayout5, @NonNull ConstraintLayout main,
      @NonNull TextView tvCalendar, @NonNull TextView tvHome, @NonNull TextView tvProfile,
      @NonNull TextView tvTimer, @NonNull LinearLayout vertLayout) {
    this.rootView = rootView;
    this.clNavBar = clNavBar;
    this.createBtn = createBtn;
    this.createCatBtn = createCatBtn;
    this.ibCalendar = ibCalendar;
    this.ibHome = ibHome;
    this.ibProfile = ibProfile;
    this.ibTimer = ibTimer;
    this.imageView = imageView;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout4 = linearLayout4;
    this.linearLayout5 = linearLayout5;
    this.main = main;
    this.tvCalendar = tvCalendar;
    this.tvHome = tvHome;
    this.tvProfile = tvProfile;
    this.tvTimer = tvTimer;
    this.vertLayout = vertLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityViewTasksBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityViewTasksBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_view_tasks, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityViewTasksBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cl_NavBar;
      ConstraintLayout clNavBar = ViewBindings.findChildViewById(rootView, id);
      if (clNavBar == null) {
        break missingId;
      }

      id = R.id.createBtn;
      FloatingActionButton createBtn = ViewBindings.findChildViewById(rootView, id);
      if (createBtn == null) {
        break missingId;
      }

      id = R.id.createCatBtn;
      FloatingActionButton createCatBtn = ViewBindings.findChildViewById(rootView, id);
      if (createCatBtn == null) {
        break missingId;
      }

      id = R.id.ib_Calendar;
      ImageButton ibCalendar = ViewBindings.findChildViewById(rootView, id);
      if (ibCalendar == null) {
        break missingId;
      }

      id = R.id.ib_Home;
      ImageButton ibHome = ViewBindings.findChildViewById(rootView, id);
      if (ibHome == null) {
        break missingId;
      }

      id = R.id.ib_Profile;
      ImageButton ibProfile = ViewBindings.findChildViewById(rootView, id);
      if (ibProfile == null) {
        break missingId;
      }

      id = R.id.ib_Timer;
      ImageButton ibTimer = ViewBindings.findChildViewById(rootView, id);
      if (ibTimer == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      ConstraintLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      ConstraintLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      ConstraintLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.linearLayout5;
      ConstraintLayout linearLayout5 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout5 == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.tv_calendar;
      TextView tvCalendar = ViewBindings.findChildViewById(rootView, id);
      if (tvCalendar == null) {
        break missingId;
      }

      id = R.id.tv_Home;
      TextView tvHome = ViewBindings.findChildViewById(rootView, id);
      if (tvHome == null) {
        break missingId;
      }

      id = R.id.tv_Profile;
      TextView tvProfile = ViewBindings.findChildViewById(rootView, id);
      if (tvProfile == null) {
        break missingId;
      }

      id = R.id.tv_timer;
      TextView tvTimer = ViewBindings.findChildViewById(rootView, id);
      if (tvTimer == null) {
        break missingId;
      }

      id = R.id.vertLayout;
      LinearLayout vertLayout = ViewBindings.findChildViewById(rootView, id);
      if (vertLayout == null) {
        break missingId;
      }

      return new ActivityViewTasksBinding((ConstraintLayout) rootView, clNavBar, createBtn,
          createCatBtn, ibCalendar, ibHome, ibProfile, ibTimer, imageView, linearLayout,
          linearLayout2, linearLayout4, linearLayout5, main, tvCalendar, tvHome, tvProfile, tvTimer,
          vertLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
