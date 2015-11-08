package com.power.max.timer.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.power.max.timer.Adapter.TimerAdapter;
import com.power.max.timer.fragments.TimerFragment;
import com.power.max.timer.R;
import com.power.max.timer.customViews.ScrimInsetsFrameLayout;
import com.power.max.timer.utilities.UtilsDevice;
import com.power.max.timer.utilities.UtilsMiscellaneous;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static double sNavigationDrawerAccountSectionAspectRatio = 9d/16d;

    private DrawerLayout mDrawerLayout;
    private LinearLayout mNavDrawerEntriesRootView;
    private FrameLayout mFrameLayout_AccountView, mFrameLayoutTimer,
            mFrameLayoutHistory, mFrameLayoutAbout;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        // Prepare RecyclerView
        //setContentView(R.layout.timer_fragment);
        mRecyclerView = (RecyclerView) findViewById(R.id.custom_timer_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = {"Entry 1", "Entry 2", "Entry 3", "Entry 4"};
        mAdapter = new TimerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Binds, creates and sets up the resources
     */
    private void initialise()
    {
        // Toolbar
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setUpIcons();

        // Layout resources
        mFrameLayout_AccountView =
                (FrameLayout) findViewById(R.id.navigation_drawer_account_view);

        mNavDrawerEntriesRootView =
                (LinearLayout)findViewById(R.id.navDrawer_root_view);

        mFrameLayoutTimer =
                (FrameLayout) findViewById(R.id.navDrawer_item_timer);

        mFrameLayoutHistory =
                (FrameLayout) findViewById(R.id.navDrawer_item_history);

        mFrameLayoutAbout =
                (FrameLayout) findViewById(R.id.navDrawer_item_about);

        // Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_activity_DrawerLayout);

        ScrimInsetsFrameLayout mScrimInsetsFrameLayout =
                (ScrimInsetsFrameLayout) findViewById(R.id.main_activity_navigation_drawer_rootLayout);

        final ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle
                (   this,
                    mDrawerLayout,
                    mToolbar,
                    R.string.navigation_drawer_opened,
                    R.string.navigation_drawer_closed
                )
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                // Disables the burger/arrow animation by default
                super.onDrawerSlide(drawerView, 0);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mActionBarDrawerToggle.syncState();

        // Navigation Drawer layout width
        final int possibleMinDrawerWidth = UtilsDevice.getScreenWidth(this) -
                UtilsMiscellaneous.getThemeAttributeDimensionSize(this, android.R.attr.actionBarSize);
        final int maxDrawerWidth = getResources().getDimensionPixelSize(R.dimen.navigation_drawer_max_width);

        mScrimInsetsFrameLayout.getLayoutParams().width =
                Math.min(possibleMinDrawerWidth, maxDrawerWidth);

        // Account section height
        mFrameLayout_AccountView.getLayoutParams().height = (int) (mScrimInsetsFrameLayout.getLayoutParams().width
                * sNavigationDrawerAccountSectionAspectRatio);

        // Nav Drawer item click listener
        mFrameLayout_AccountView.setOnClickListener(this);
        mFrameLayoutTimer.setOnClickListener(this);
        mFrameLayoutHistory.setOnClickListener(this);
        mFrameLayoutAbout.setOnClickListener(this);

        // Set the first item as selected for the first time
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.nav_timer);
        }

        mFrameLayoutTimer.setSelected(true);

        // Create the first fragment to be shown
        final Bundle bundle = new Bundle();
        bundle.putInt(TimerFragment.sARGUMENT_TIMER_CODE, 0);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_content_frame, TimerFragment.newInstance(bundle))
                .commit();
    }

    @Override
    public void onClick(View view)
    {
        if (view == mFrameLayout_AccountView)
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);

            // Start account/signup/signin
            // startActivity(new Intent(view.getContext(), AccountActivity.class));
        }
        else
        {
            if (!view.isSelected()) {
                onRowPressed((FrameLayout) view);

                if (view == mFrameLayoutTimer) {
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(getString(R.string.nav_timer));
                    }

                    view.setSelected(true);

                    final Bundle bundle = new Bundle();
                    bundle.putInt(TimerFragment.sARGUMENT_TIMER_CODE, 0);

                    // Insert the fragment by replacing any existing fragment
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_activity_content_frame, TimerFragment.newInstance(bundle))
                            .commit();
                }
                else if (view == mFrameLayoutHistory) {
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(getString(R.string.nav_history));
                    }

                    view.setSelected(true);

                    final Bundle bundle = new Bundle();
                    bundle.putInt(TimerFragment.sARGUMENT_TIMER_CODE, 1);

                    // Insert the fragment by replacing any existing fragment
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_activity_content_frame, TimerFragment.newInstance(bundle))
                            .commit();
                }
                else if (view == mFrameLayoutAbout) {
                    // Show about activity
                    // startActivity(new Intent(view.getContext(), AboutActivity.class));
                }
            }
            else {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        }
    }

    /**
     * Set up the rows when any is pressed
     *
     * @param pressedRow is the pressed row in the drawer
     */
    private void onRowPressed(@NonNull final FrameLayout pressedRow) {
        if (pressedRow.getTag() != getResources().getString(R.string.tag_nav_drawer_special_entry)) {
            for (int i = 0; i < mNavDrawerEntriesRootView.getChildCount(); i++) {
                final View currentView = mNavDrawerEntriesRootView.getChildAt(i);

                final boolean currentViewIsMainEntry = currentView.getTag() ==
                        getResources().getString(R.string.tag_nav_drawer_main_entry);

                if (currentViewIsMainEntry) {
                    currentView.setSelected(currentView == pressedRow);
                }
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    /**
     * Sets a tint list to the icons
     *
     * Uses DrawableCompat to make it work before SKD 21
     */
    private void setUpIcons()
    {
        // Icons tint list
        final ImageView homeImageView =
                (ImageView) findViewById(R.id.navigation_drawer_items_list_icon_home);
        final Drawable homeDrawable = DrawableCompat.wrap(homeImageView.getDrawable());
        DrawableCompat.setTintList
                (
                        homeDrawable.mutate(),
                        ContextCompat.getColorStateList(this, R.color.nav_drawer_row_icon)
                );

        homeImageView.setImageDrawable(homeDrawable);

        final ImageView exploreImageView =
                (ImageView) findViewById(R.id.navigation_drawer_items_list_icon_explore);
        final Drawable exploreDrawable = DrawableCompat.wrap(exploreImageView.getDrawable());
        DrawableCompat.setTintList
                (
                        exploreDrawable.mutate(),
                        ContextCompat.getColorStateList(this, R.color.nav_drawer_row_icon)
                );

        exploreImageView.setImageDrawable(exploreDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
