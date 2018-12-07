package pk.company.potrosackakorpa;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFAB;
    private View mFadeBackground;
    private NewListFragment newListFragment;

    private boolean mPressedTwice = false; // used in onBackPressed to let user exit the app with two clicks on back button
    private boolean mIsNewsListVisible = false; // used to see if the "NewListFragment" is visible

    private ArrayList<Lists> lists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        initToolBar();
        drawerController();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add items to the list here
        // This is just an example
        Lists list = new Lists("Šoping lista test1", "grey", "22.06.2018 - 13:00");
        Lists list2 = new Lists("Šoping lista test2", "green", "22.06.2018 - 14:00");
        Lists list3 = new Lists("Šoping lista test3", "blue", "22.06.2018 - 15:00");
        Lists list4 = new Lists("Šoping lista test4", "red", "22.06.2018 - 16:00");

        lists.add(list);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);

        ListsAdapter listAdapter = new ListsAdapter(mContext, lists);

        mRecyclerView.setAdapter(listAdapter);

        // FAB Handling
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsNewsListVisible) {
                    mIsNewsListVisible = true;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    newListFragment = new NewListFragment();
                    fragmentTransaction.add(R.id.fragment_container, newListFragment);
                    fragmentTransaction.commit();

                    mFadeBackground.setVisibility(View.VISIBLE);
                    mFadeBackground.animate().alpha(0.5f);

                    mFAB.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initToolBar() {
        Toolbar toolbar = findViewById(R.id.homeToolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.homeToolBarTitle);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        toolbar.inflateMenu(R.menu.home_toolbar_menu);

    }

    public void drawerController() {
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.nav_lists); // First item to be checked as default

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
    }

    private void initViews() {
        mContext = this;
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mRecyclerView = findViewById(R.id.listsListView);
        mFAB = findViewById(R.id.floatingActionButton);
        mFadeBackground = findViewById(R.id.fadeBackground);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onBackPressed() {
        if (!mIsNewsListVisible) {
            if (mPressedTwice) {
                super.onBackPressed();
                this.finishAffinity();
            }

            this.mPressedTwice = true;
            Toast.makeText(this, "Pritisnite nazad dugme još jednom da bi ste izašli", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPressedTwice = false;
                }
            }, 2000);
        } else {
            mFAB.setVisibility(View.VISIBLE);
            mFadeBackground.animate().alpha(0.0f);
            mFadeBackground.setVisibility(View.GONE);
            mIsNewsListVisible = false;


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.remove(newListFragment);
            fragmentTransaction.commit();


        }
    }
}
