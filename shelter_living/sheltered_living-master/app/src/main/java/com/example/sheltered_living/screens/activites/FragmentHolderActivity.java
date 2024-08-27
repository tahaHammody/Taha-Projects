package com.example.sheltered_living.screens.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.sheltered_living.R;
import com.example.sheltered_living.screens.fragments.StaffLoginFragment;
import com.example.sheltered_living.screens.fragments.StaffProfileFragment;
import com.example.sheltered_living.screens.fragments.AboutFragment;
import com.example.sheltered_living.screens.fragments.AddOrUpdateFragment;
import com.example.sheltered_living.screens.fragments.ContactManagerFragment;
import com.example.sheltered_living.screens.fragments.ManagerLoginFragment;

public class FragmentHolderActivity extends AppCompatActivity {

    public final static String FRAGMENT_TYPE = "FRAGMENT_TYPE";
    public enum FragmentType {
        MANAGER_LOGIN,
        STUFF_MEMBER_LOGIN,
        ADD_UPDATE_TASK,
        PROFILE,
        CONTACT,
        ABOUT_ME
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);
        FragmentType type = (FragmentType) getIntent().getSerializableExtra(FRAGMENT_TYPE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = null;
        switch (type) {
            case MANAGER_LOGIN:
                fragment = new ManagerLoginFragment();
                break;
            case STUFF_MEMBER_LOGIN:
                fragment = new StaffLoginFragment();
                break;
            case ADD_UPDATE_TASK:
                fragment = new AddOrUpdateFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                bundle.putSerializable("staffMemberPosition", getIntent().getIntExtra("staffMemberPosition", 0));
                bundle.putSerializable("taskPosition", getIntent().getIntExtra("taskPosition", -1));
                fragment.setArguments(bundle);
                break;
            case PROFILE:
                fragment = new StaffProfileFragment();
                break;
            case CONTACT:
                fragment = new ContactManagerFragment();
                break;
            case ABOUT_ME:
                fragment = new AboutFragment();
                break;

        }
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
