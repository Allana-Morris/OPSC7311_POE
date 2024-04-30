// Generated by view binder compiler. Do not edit!
package com.example.opsc7311poe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.opsc7311poe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TaskListingBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imgTaskIcon;

  @NonNull
  public final TextView tvRecorded;

  @NonNull
  public final TextView tvTaskName;

  @NonNull
  public final TextView tvTaskTime;

  @NonNull
  public final View view5;

  private TaskListingBinding(@NonNull CardView rootView, @NonNull ImageView imgTaskIcon,
      @NonNull TextView tvRecorded, @NonNull TextView tvTaskName, @NonNull TextView tvTaskTime,
      @NonNull View view5) {
    this.rootView = rootView;
    this.imgTaskIcon = imgTaskIcon;
    this.tvRecorded = tvRecorded;
    this.tvTaskName = tvTaskName;
    this.tvTaskTime = tvTaskTime;
    this.view5 = view5;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static TaskListingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TaskListingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.task_listing, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TaskListingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgTaskIcon;
      ImageView imgTaskIcon = ViewBindings.findChildViewById(rootView, id);
      if (imgTaskIcon == null) {
        break missingId;
      }

      id = R.id.tvRecorded;
      TextView tvRecorded = ViewBindings.findChildViewById(rootView, id);
      if (tvRecorded == null) {
        break missingId;
      }

      id = R.id.tvTask_name;
      TextView tvTaskName = ViewBindings.findChildViewById(rootView, id);
      if (tvTaskName == null) {
        break missingId;
      }

      id = R.id.tvTask_time;
      TextView tvTaskTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTaskTime == null) {
        break missingId;
      }

      id = R.id.view5;
      View view5 = ViewBindings.findChildViewById(rootView, id);
      if (view5 == null) {
        break missingId;
      }

      return new TaskListingBinding((CardView) rootView, imgTaskIcon, tvRecorded, tvTaskName,
          tvTaskTime, view5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}