package com.example.sannidhya.yoga;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class homes extends AppCompatActivity {
    Singleton globals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing global variables
        globals = Singleton.getInstance();
        setContentView(R.layout.activity_homes);
        //creating tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Home"));
        tabs.addTab(tabs.newTab().setText("Favorites"));
        tabs.addTab(tabs.newTab().setText("Support"));
        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.old_stuff_list) ;
        //initializing category list

        //ListView old_videos = (ListView) findViewById(R.id.old_stuff_list);
        //setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        //TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //checking for old played video if exists
        //click happens in view holder for this list

        /*if (globals.resune_list.isEmpty() == false){
            LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
            layoutManager.scrollToPosition(0);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setHasFixedSize(true);



            oldlistAdapter oldlistAdapter = new oldlistAdapter(  globals.resune_list);

            for (int i = 0; i < globals.resune_list.size(); i++){
                Log.d("resume list","video id :"+ globals.resune_list.get(i).getVideo_id()+" Date: " + globals.resune_list.get(i).getWhenDidYouWatch());
            }
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(oldlistAdapter);
            /*recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(homes.this,category_list.get(position).getmCategory_name(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(homes.this,media_page.class).putExtra("name",globals.resune_list.get(position).getCategory()).putExtra("vid_id",globals.resune_list.get(position).getVideo_id()));
                }
            });
        }
        else{
            recyclerView.setVisibility(View.INVISIBLE);
        }*/

        //initialize list view
        /*Listadapter listadapter = new Listadapter(this,category_list);

        ListView listView = (ListView) findViewById(R.id.listview_category);
        listView.setAdapter(listadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(homes.this,category_list.get(position).getmCategory_name(),Toast.LENGTH_LONG).show();
                //goto media page if any category selected
                startActivity(new Intent(homes.this,media_page.class).putExtra("name",category_list.get(position).getmCategory_name()));
            }
        });*/


    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter( getSupportFragmentManager() );
        adapter.addFragment(new CardFrag(globals), "HOME");
        adapter.addFragment(new FavFrag(), "FAVORITES");
        adapter.addFragment(new SupportFrag(), "SUPPORT");
        viewPager.setAdapter(adapter);
    }
    //setting up fragment page adapter
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get( position );
        }
    }

}
