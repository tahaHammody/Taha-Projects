package com.example.sheltered_living.screens.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.StaffMember;

import java.util.Calendar;

public class CalenderFragment extends DialogFragment {

    private int position;
    private Runnable onClose;
    private Calendar calendar;

    public static void openDialog(FragmentManager fm, int position, Runnable onClose) {
        CalenderFragment fragment =  new CalenderFragment();
        fragment.position = position;
        fragment.onClose = onClose;
        fragment.calendar = Calendar.getInstance();
        fragment.show(fm, "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_shift_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView calendarView = view.findViewById(R.id.calender);
        Calendar c = Calendar.getInstance();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                c.set(year, month, dayOfMonth);
                calendar.clear();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });
        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.getDate();
                AlertDialogUtils.showAlertDialogPositiveNegative(
                        CalenderFragment.this.getContext(), R.string.please, R.string.are_you_sure
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StaffMember staffMember = SessionManager.manager.getStaffMembers().get(position);
                                if (isNewShift()) {
                                    staffMember.getShifts().put(String.valueOf(calendar.getTimeInMillis()), calendar.getTimeInMillis());
                                    FirebaseDataBaseManager.updateStaffMemberShifts(staffMember);
                                    SqlDataBaseManager.database.insertShift(staffMember, String.valueOf(calendar.getTimeInMillis()), null);
                                    Toast.makeText(CalenderFragment.this.getActivity().getApplicationContext(), getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                                    onClose.run();
                                    getParentFragmentManager().beginTransaction().remove(CalenderFragment.this).commit();
                                }else {
                                    AlertDialogUtils.showAlertDialog(CalenderFragment.this.getActivity(), getString(R.string.error), getString(R.string.this_time_already_exist_in_your_shifts));
                                }
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
            }
        });
    }

    private boolean isNewShift(){
        StaffMember staffMember = SessionManager.manager.getStaffMembers().get(position);
        return staffMember.getShifts().getOrDefault(String.valueOf(calendar.getTimeInMillis()), 0L) == 0L;
    }

}
