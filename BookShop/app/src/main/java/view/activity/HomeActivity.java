package view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bookshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import view.fragment.FragmentDiscover;
import view.fragment.FragmentHome;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

        }
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                getSupportFragmentManager().beginTransaction().setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, new FragmentHome()).commit();
            }
            if (item.getItemId() == R.id.discover) {
                getSupportFragmentManager().beginTransaction().setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, new FragmentDiscover()).commit();
            }
//            if (item.getItemId() == R.id.podcast) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PodcastFragment()).commit();
//            }
//            if (item.getItemId() == R.id.menu) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
//            }
//
//
            return true;
        });


    }
}