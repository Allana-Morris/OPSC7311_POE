// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
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

public final class ActivityTotalCatHoursBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnSelectHours;

  @NonNull
  public final View chooseWeek;

  @NonNull
  public final ScrollView chooseWeek1;

  @NonNull
  public final ConstraintLayout clNavBar1;

  @NonNull
  public final ImageButton ibCalendar1;

  @NonNull
  public final ImageButton ibHome1;

  @NonNull
  public final ImageButton ibProfile1;

  @NonNull
  public final ImageButton ibTimer1;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ConstraintLayout linearLayout1;

  @NonNull
  public final ConstraintLayout linearLayout21;

  @NonNull
  public final ConstraintLayout linearLayout41;

  @NonNull
  public final ConstraintLayout linearLayout51;

  @NonNull
  public final ConstraintLayout main1;

  @NonNull
  public final TextView tvCalendar1;

  @NonNull
  public final TextView tvEndDate1;

  @NonNull
  public final TextView tvHome1;

  @NonNull
  public final TextView tvProfile1;

  @NonNull
  public final TextView tvSelectPeriod1;

  @NonNull
  public final TextView tvStartDate1;

  @NonNull
  public final TextView tvTimer1;

  @NonNull
  public final TextView tvTotal;

  @NonNull
  public final TextView tvgenerate1;

  private ActivityTotalCatHoursBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnSelectHours, @NonNull View chooseWeek, @NonNull ScrollView chooseWeek1,
      @NonNull ConstraintLayout clNavBar1, @NonNull ImageButton ibCalendar1,
      @NonNull ImageButton ibHome1, @NonNull ImageButton ibProfile1, @NonNull ImageButton ibTimer1,
      @NonNull ImageView imageView, @NonNull ConstraintLayout linearLayout1,
      @NonNull ConstraintLayout linearLayout21, @NonNull ConstraintLayout linearLayout41,
      @NonNull ConstraintLayout linearLayout51, @NonNull ConstraintLayout main1,
      @NonNull TextView tvCalendar1, @NonNull TextView tvEndDate1, @NonNull TextView tvHome1,
      @NonNull TextView tvProfile1, @NonNull TextView tvSelectPeriod1,
      @NonNull TextView tvStartDate1, @NonNull TextView tvTimer1, @NonNull TextView tvTotal,
      @NonNull TextView tvgenerate1) {
    this.rootView = rootView;
    this.btnSelectHours = btnSelectHours;
    this.chooseWeek = chooseWeek;
    this.chooseWeek1 = chooseWeek1;
    this.clNavBar1 = clNavBar1;
    this.ibCalendar1 = ibCalendar1;
    this.ibHome1 = ibHome1;
    this.ibProfile1 = ibProfile1;
    this.ibTimer1 = ibTimer1;
    this.imageView = imageView;
    this.linearLayout1 = linearLayout1;
    this.linearLayout21 = linearLayout21;
    this.linearLayout41 = linearLayout41;
    this.linearLayout51 = linearLayout51;
    this.main1 = main1;
    this.tvCalendar1 = tvCalendar1;
    this.tvEndDate1 = tvEndDate1;
    this.tvHome1 = tvHome1;
    this.tvProfile1 = tvProfile1;
    this.tvSelectPeriod1 = tvSelectPeriod1;
    this.tvStartDate1 = tvStartDate1;
    this.tvTimer1 = tvTimer1;
    this.tvTotal = tvTotal;
    this.tvgenerate1 = tvgenerate1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTotalCatHoursBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTotalCatHoursBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_total_cat_hours, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTotalCatHoursBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSelectHours;
      Button btnSelectHours = ViewBindings.findChildViewById(rootView, id);
      if (btnSelectHours == null) {
        break missingId;
      }

      id = R.id.chooseWeek;
      View chooseWeek = ViewBindings.findChildViewById(rootView, id);
      if (chooseWeek == null) {
        break missingId;
      }

      id = R.id.chooseWeek1;
      ScrollView chooseWeek1 = ViewBindings.findChildViewById(rootView, id);
      if (chooseWeek1 == null) {
        break missingId;
      }

      id = R.id.cl_NavBar1;
      ConstraintLayout clNavBar1 = ViewBindings.findChildViewById(rootView, id);
      if (clNavBar1 == null) {
        break missingId;
      }

      id = R.id.ib_Calendar1;
      ImageButton ibCalendar1 = ViewBindings.findChildViewById(rootView, id);
      if (ibCalendar1 == null) {
        break missingId;
      }

      id = R.id.ib_Home1;
      ImageButton ibHome1 = ViewBindings.findChildViewById(rootView, id);
      if (ibHome1 == null) {
        break missingId;
      }

      id = R.id.ib_Profile1;
      ImageButton ibProfile1 = ViewBindings.findChildViewById(rootView, id);
      if (ibProfile1 == null) {
        break missingId;
      }

      id = R.id.ib_Timer1;
      ImageButton ibTimer1 = ViewBindings.findChildViewById(rootView, id);
      if (ibTimer1 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.linearLayout1;
      ConstraintLayout linearLayout1 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout1 == null) {
        break missingId;
      }

      id = R.id.linearLayout21;
      ConstraintLayout linearLayout21 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout21 == null) {
        break missingId;
      }

      id = R.id.linearLayout41;
      ConstraintLayout linearLayout41 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout41 == null) {
        break missingId;
      }

      id = R.id.linearLayout51;
      ConstraintLayout linearLayout51 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout51 == null) {
        break missingId;
      }

      ConstraintLayout main1 = (ConstraintLayout) rootView;

      id = R.id.tv_calendar1;
      TextView tvCalendar1 = ViewBindings.findChildViewById(rootView, id);
      if (tvCalendar1 == null) {
        break missingId;
      }

      id = R.id.tvEndDate1;
      TextView tvEndDate1 = ViewBindings.findChildViewById(rootView, id);
      if (tvEndDate1 == null) {
        break missingId;
      }

      id = R.id.tv_Home1;
      TextView tvHome1 = ViewBindings.findChildViewById(rootView, id);
      if (tvHome1 == null) {
        break missingId;
      }

      id = R.id.tv_Profile1;
      TextView tvProfile1 = ViewBindings.findChildViewById(rootView, id);
      if (tvProfile1 == null) {
        break missingId;
      }

      id = R.id.tvSelectPeriod1;
      TextView tvSelectPeriod1 = ViewBindings.findChildViewById(rootView, id);
      if (tvSelectPeriod1 == null) {
        break missingId;
      }

      id = R.id.tvStartDate1;
      TextView tvStartDate1 = ViewBindings.findChildViewById(rootView, id);
      if (tvStartDate1 == null) {
        break missingId;
      }

      id = R.id.tv_timer1;
      TextView tvTimer1 = ViewBindings.findChildViewById(rootView, id);
      if (tvTimer1 == null) {
        break missingId;
      }

      id = R.id.tv_Total;
      TextView tvTotal = ViewBindings.findChildViewById(rootView, id);
      if (tvTotal == null) {
        break missingId;
      }

      id = R.id.tvgenerate1;
      TextView tvgenerate1 = ViewBindings.findChildViewById(rootView, id);
      if (tvgenerate1 == null) {
        break missingId;
      }

      return new ActivityTotalCatHoursBinding((ConstraintLayout) rootView, btnSelectHours,
          chooseWeek, chooseWeek1, clNavBar1, ibCalendar1, ibHome1, ibProfile1, ibTimer1, imageView,
          linearLayout1, linearLayout21, linearLayout41, linearLayout51, main1, tvCalendar1,
          tvEndDate1, tvHome1, tvProfile1, tvSelectPeriod1, tvStartDate1, tvTimer1, tvTotal,
          tvgenerate1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
