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
import com.github.mikephil.charting.charts.BarChart;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityViewGraphTotalHoursBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BarChart barChart;

  @NonNull
  public final Button button;

  @NonNull
  public final View chooseWeek;

  @NonNull
  public final EditText editTextDate;

  @NonNull
  public final EditText editTextDate2;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  private ActivityViewGraphTotalHoursBinding(@NonNull ConstraintLayout rootView,
      @NonNull BarChart barChart, @NonNull Button button, @NonNull View chooseWeek,
      @NonNull EditText editTextDate, @NonNull EditText editTextDate2, @NonNull ImageView imageView,
      @NonNull ConstraintLayout main, @NonNull TextView textView2, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.barChart = barChart;
    this.button = button;
    this.chooseWeek = chooseWeek;
    this.editTextDate = editTextDate;
    this.editTextDate2 = editTextDate2;
    this.imageView = imageView;
    this.main = main;
    this.textView2 = textView2;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityViewGraphTotalHoursBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityViewGraphTotalHoursBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_view_graph_total_hours, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityViewGraphTotalHoursBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.barChart;
      BarChart barChart = ViewBindings.findChildViewById(rootView, id);
      if (barChart == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.chooseWeek;
      View chooseWeek = ViewBindings.findChildViewById(rootView, id);
      if (chooseWeek == null) {
        break missingId;
      }

      id = R.id.editTextDate;
      EditText editTextDate = ViewBindings.findChildViewById(rootView, id);
      if (editTextDate == null) {
        break missingId;
      }

      id = R.id.editTextDate2;
      EditText editTextDate2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextDate2 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new ActivityViewGraphTotalHoursBinding((ConstraintLayout) rootView, barChart, button,
          chooseWeek, editTextDate, editTextDate2, imageView, main, textView2, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
