package id.application.todolist;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import id.application.todolist.fragment.FragmentCalendar;
import id.application.todolist.fragment.FragmentProfile;
import id.application.todolist.fragment.FragmentTask;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigation;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        navigation = findViewById(R.id.nav_view);

        frameLayout = findViewById(R.id.framelayout);
        bottomNavigation = findViewById(R.id.bottomnav);

        // Handle BottomNavigationView item selection
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Mendapatkan referensi ke DrawerLayout
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

                if (item.getItemId() == R.id.task_activity) {
                    selectedFragment = new FragmentTask();
                } else if (item.getItemId() == R.id.calendar_activity) {
                    selectedFragment = new FragmentCalendar();
                } else if (item.getItemId() == R.id.profile_activity) {
                    selectedFragment = new FragmentProfile();
                } else if (item.getItemId() == R.id.navigation_drawable) {
                    // Buka Navigation Drawer saat item navigation_drawable diklik
                    if (drawerLayout != null) {
                        drawerLayout.openDrawer(GravityCompat.START); // Buka drawer dari kiri
                    }
                    return false;
                }

                // Jika ada fragment yang dipilih, lakukan penggantian fragment
                if (selectedFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout, selectedFragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });



        // Set default fragment when activity starts
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.task_activity); // Set default selected item
        }
    }
}
