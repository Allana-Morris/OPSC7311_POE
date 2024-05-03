// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextClock;
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

public final class ActivityTimerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigationView;

  @NonNull
  public final Button btnReset;

  @NonNull
  public final Button btnStart;

  @NonNull
  public final Button btnStop;

  @NonNull
  public final ConstraintLayout clNavBar;

  @NonNull
  public final TextClock edtClock;

  @NonNull
  public final ImageButton ibCalendar;

  @NonNull
  public final ImageButton ibHome;

  @NonNull
  public final ImageButton ibProfile;

  @NonNull
  public final ImageButton ibTimer;

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
  public final Button saveTimebtn;

  @NonNull
  public final Spinner spinCat;

  @NonNull
  public final Spinner spinTask;

  @NonNull
  public final TextView tvCalendar;

  @NonNull
  public final TextView tvHome;

  @NonNull
  public final TextView tvProfile;

  @NonNull
  public final TextView tvTimer;

  @NonNull
  public final TextView tvTimer1;

  private ActivityTimerBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomNavigationView, @NonNull Button btnReset,
      @NonNull Button btnStart, @NonNull Button btnStop, @NonNull ConstraintLayout clNavBar,
      @NonNull TextClock edtClock, @NonNull ImageButton ibCalendar, @NonNull ImageButton ibHome,
      @NonNull ImageButton ibProfile, @NonNull ImageButton ibTimer,
      @NonNull ConstraintLayout linearLayout, @NonNull ConstraintLayout linearLayout2,
      @NonNull ConstraintLayout linearLayout4, @NonNull ConstraintLayout linearLayout5,
      @NonNull ConstraintLayout main, @NonNull Button saveTimebtn, @NonNull Spinner spinCat,
      @NonNull Spinner spinTask, @NonNull TextView tvCalendar, @NonNull TextView tvHome,
      @NonNull TextView tvProfile, @NonNull TextView tvTimer, @NonNull TextView tvTimer1) {
    this.rootView = rootView;
    this.bottomNavigationView = bottomNavigationView;
    this.btnReset = btnReset;
    this.btnStart = btnStart;
    this.btnStop = btnStop;
    this.clNavBar = clNavBar;
    this.edtClock = edtClock;
    this.ibCalendar = ibCalendar;
    this.ibHome = ibHome;
    this.ibProfile = ibProfile;
    this.ibTimer = ibTimer;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.linearLayout4 = linearLayout4;
    this.linearLayout5 = linearLayout5;
    this.main = main;
    this.saveTimebtn = saveTimebtn;
    this.spinCat = spinCat;
    this.spinTask = spinTask;
    this.tvCalendar = tvCalendar;
    this.tvHome = tvHome;
    this.tvProfile = tvProfile;
    this.tvTimer = tvTimer;
    this.tvTimer1 = tvTimer1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTimerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTimerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_timer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTimerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNavigationView;
      BottomNavigationView bottomNavigationView = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigationView == null) {
        break missingId;
      }

      id = R.id.btnReset;
      Button btnReset = ViewBindings.findChildViewById(rootView, id);
      if (btnReset == null) {
        break missingId;
      }

      id = R.id.btnStart;
      Button btnStart = ViewBindings.findChildViewById(rootView, id);
      if (btnStart == null) {
        break missingId;
      }

      id = R.id.btnStop;
      Button btnStop = ViewBindings.findChildViewById(rootView, id);
      if (btnStop == null) {
        break missingId;
      }

      id = R.id.cl_NavBar;
      ConstraintLayout clNavBar = ViewBindings.findChildViewById(rootView, id);
      if (clNavBar == null) {
        break missingId;
      }

      id = R.id.edtClock;
      TextClock edtClock = ViewBindings.findChildViewById(rootView, id);
      if (edtClock == null) {
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

      id = R.id.saveTimebtn;
      Button saveTimebtn = ViewBindings.findChildViewById(rootView, id);
      if (saveTimebtn == null) {
        break missingId;
      }

      id = R.id.spinCat;
      Spinner spinCat = ViewBindings.findChildViewById(rootView, id);
      if (spinCat == null) {
        break missingId;
      }

      id = R.id.spinTask;
      Spinner spinTask = ViewBindings.findChildViewById(rootView, id);
      if (spinTask == null) {
        break missingId;
      }

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

      id = R.id.tvTimer;
      TextView tvTimer = ViewBindings.findChildViewById(rootView, id);
      if (tvTimer == null) {
        break missingId;
      }

      id = R.id.tv_timer;
      TextView tvTimer1 = ViewBindings.findChildViewById(rootView, id);
      if (tvTimer1 == null) {
        break missingId;
      }

      return new ActivityTimerBinding((ConstraintLayout) rootView, bottomNavigationView, btnReset,
          btnStart, btnStop, clNavBar, edtClock, ibCalendar, ibHome, ibProfile, ibTimer,
          linearLayout, linearLayout2, linearLayout4, linearLayout5, main, saveTimebtn, spinCat,
          spinTask, tvCalendar, tvHome, tvProfile, tvTimer, tvTimer1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
