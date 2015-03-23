MaterialTabsAdvanced [![maven](https://img.shields.io/github/tag/jjhesk/MaterialTabsAdavanced.svg?label=maven)](https://jitpack.io/#jjhesk/MaterialTabsAdavanced/) [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17) [![gitpay](http://fc07.deviantart.net/fs70/f/2012/336/f/9/little_pixel_heart_by_tiny_bear-d5mtwiu.gif)](https://gratipay.com/jjhesk/)
============

Custom Tabs with Material Design animations for pre-Lollipop devices<br>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialTabs-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1105) 


[Download example apk](https://raw.github.com/neokree/MaterialTabs/master/example.apk)

It requires 14+ API and android support v7 (Toolbar)

# Developer notes

Dependency: [Android-UI](https://github.com/markushi/android-ui) Reveal Color View <br>
If you are using MaterialTabs in your app and would like to be listed here, please let me know via [email](mailto:hesk.kam@101medialab.com)! <br>

<h3>How to use:</h3>
define it in xml layout with custom attributes
```xml
<!-- for Text Tabs -->
<hkm.ui.materialtabs.MaterialTabHost
        android:id="@+id/materialTabHost"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:textColor="#FFFFFF"
        app:materialTabsPrimaryColor="YOUR_PRIMARY_COLOR"
        app:nonFixTabsCountStart="4"
        app:accentColor="YOUR_ACCENT_COLOR" />
<!-- for icon tabs --> 
<hkm.ui.materialtabs.MaterialTabHost
        android:id="@+id/materialTabHost"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:iconColor="#FFFFFF"
        app:materialTabsPrimaryColor="YOUR_PRIMARY_COLOR"
        app:accentColor="YOUR_ACCENT_COLOR"
        app:nonFixTabsCountStart="4"
        app:hasIcons="true"/>
```
<em>( I'm working on use wrap_content instead 48dp)</em>

Connect to java code and add to viewPager
```java
MaterialTabHost tabHost;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
		pager = (ViewPager) this.findViewById(R.id.viewpager);
		
		// init view pager
		pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	// when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
		
		// insert all tabs from pagerAdapter data
		for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab() 
                            .setIcon(getIcon(i))
                            .setTabListener(this)
                            );
    }
}

@Override
	public void onTabSelected(MaterialTab tab) {
		// when the tab is clicked the pager swipe content to the tab position
		pager.setCurrentItem(tab.getPosition());
		
	}
```

N.B. Your activity must <code>extends ActionBarActivity implements MaterialTabListener</code>


### How to import
###### Android Studio
Add this to your build.gradle:
```java 
dependencies {
	        compile 'com.github.jjhesk:MaterialTabsAdavanced:v0.15'
	}
```

<h3>Limitations</h3>
Actually, this library have some limitations: 
- No selector animations

These problems are currently in development

### Fixed and Scrollable tabs. 
###### With text tabs
N default  = 3
[1 - N] Fixed Tabs <br>
[N - &infin;] Scrollable Tabs
###### With icon tabs
[1 - 5] Fixed Tabs <br>
[6 - &infin;] Scrollable Tabs

<img src="https://raw.github.com/neokree/MaterialTabs/master/screen.jpg" alt="screenshot" width="300px" height="auto" />
<img src="https://raw.github.com/neokree/MaterialTabs/master/screen-icon.jpg" alt="screenshot" width="300px" height="auto" />
<img src="https://raw.github.com/neokree/MaterialTabs/master/screen-multitab.jpg" alt="screenshot" width="300px" height="auto" />
<img src="https://raw.github.com/neokree/MaterialTabs/master/screen-tablet.jpg" alt="screenshot" width="600px" height="auto" />
