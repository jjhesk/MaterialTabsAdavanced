MaterialTabsAdvanced  [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17) [![gitpay](http://fc07.deviantart.net/fs70/f/2012/336/f/9/little_pixel_heart_by_tiny_bear-d5mtwiu.gif)](https://gratipay.com/jjhesk/)  
============

Custom Tabs with Material Design animations for pre-Lollipop devices<br>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialTabs-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1105)
[Download example apk](https://raw.github.com/neokree/MaterialTabs/master/example.apk)
It requires 14+ API and android support v7 (Toolbar)

# Developer notes

Dependency: [Android-UI](https://github.com/markushi/android-ui) Reveal Color View <br>
If you are using MaterialTabs in your app and would like to be listed here, please let me know via [email](mailto:hesk.kam@101medialab.com)! <br>

# Release features
v1.0.1
- [x] - Support custom font and custom textview by external textview library
- [x] - Single line implementations
- [x] - Adaptive layout and extra frames

<h3>How to use:</h3>
define it in xml layout with custom attributes

setup your own attributes from the xml from [here](https://github.com/jjhesk/MaterialTabsAdavanced/blob/master/MaterialTabsModule/src/main/res/values/attrs.xml)

```xml
<!-- for Text Tabs -->
<hkm.ui.materialtabs.MaterialTabHost
        android:id="@+id/materialTabHost"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:advtabs_textColor="#FFFFFF"
        app:advtabs_materialTabsPrimaryColor="YOUR_PRIMARY_COLOR"
        app:advtabs_nonFixTabsCountStart="4"
        app:advtabs_accentColor="YOUR_ACCENT_COLOR" />
<!-- for icon tabs -->
<hkm.ui.materialtabs.MaterialTabHost
        android:id="@+id/materialTabHost"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:advtabs_iconColor="#FFFFFF"
        app:advtabs_materialTabsPrimaryColor="YOUR_PRIMARY_COLOR"
        app:advtabs_accentColor="YOUR_ACCENT_COLOR"
        app:advtabs_nonFixTabsCountStart="4"
        app:advtabs_hasIcons="true"/>
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

###add new tab

Just the text only
```java
private MaterialTabHost tab;


tab.addTab(tab.createCustomTextTab(R.layout.item_tab, txt, false).setTabListener(this));

```
#### R.layout.item_tab
That will be consist of your own table arrangement implementations.



It is required to apply with the callback interface from using setTabListener
```java
extends ActionBarActivity implements MaterialTabListener
```

## Installations
Please check with supported repo mavens

###jitpack remote
[![maven](https://img.shields.io/github/tag/jjhesk/MaterialTabsAdavanced.svg?label=maven)](https://jitpack.io/#jjhesk/MaterialTabsAdavanced/)
```gradle
repositories {
    maven {  url "https://jitpack.io"  }
}

dependencies {
	        compile 'com.github.jjhesk:MaterialTabsAdavanced:vX.XX'
	}
```

###Bintray Remote 
[ ![Download](https://api.bintray.com/packages/jjhesk/maven/advancedmaterialtabs/images/download.svg) ](https://bintray.com/jjhesk/maven/advancedmaterialtabs/_latestVersion)
```gradle

repositories {
    maven {  url "http://dl.bintray.com/jjhesk/maven"  }
}

dependencies {
     compile 'com.hkm:advancedmaterialtabs:1.0.0@aar'
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


Application using MaterialTabsAdavanced
======
![HBStore](https://lh3.googleusercontent.com/UP36oSco0gsi_jXI56sQw8vSWip5IyaFwUCrfBnxaXNbpF1WfydB6jmYNYU5DY5egg=w300-rw)

[HBStore] (https://play.google.com/store/apps/details?id=com.hypebeast.store&hl=en)		-

