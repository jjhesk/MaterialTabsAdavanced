package it.neokree.materialtabs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;


/**
 * A Toolbar that contains multiple tabs
 *
 * @author neokree
 */
@SuppressLint("InflateParams")
public class MaterialTabHost extends RelativeLayout implements View.OnClickListener {

    public static final int BESTFIT = 1, ITEMBASE = 2;


    private int primaryColor;
    private int accentColor;
    private int textColor;
    private int iconColor;
    private List<MaterialTab> tabs;
    private boolean hasIcons;
    private boolean isTablet;
    private float density;
    private boolean scrollable;
    private int fixtablimit;
    private HorizontalScrollView scrollView;
    private LinearLayout layout;
    private ImageButton left;
    private ImageButton right;
    private int custom_tab_layout_id;
    private static int tabSelected;
    private int borderwidth, borderColor;
    private boolean borderEdge = false;
    private int fittype = ITEMBASE;


    public MaterialTabHost(Context context) {
        this(context, null);
    }

    public MaterialTabHost(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MaterialTabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        scrollView = new HorizontalScrollView(context);
        scrollView.setOverScrollMode(HorizontalScrollView.OVER_SCROLL_NEVER);
        scrollView.setHorizontalScrollBarEnabled(false);
        layout = new LinearLayout(context);
        scrollView.addView(layout);

        // get attributes
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialTabHost, 0, 0);

            try {
                // custom attributes
                hasIcons = a.getBoolean(R.styleable.MaterialTabHost_advtabs_hasIcons, false);
                primaryColor = a.getColor(R.styleable.MaterialTabHost_advtabs_materialTabsPrimaryColor, Color.parseColor("#009688"));
                accentColor = a.getColor(R.styleable.MaterialTabHost_advtabs_accentColor, Color.parseColor("#00b0ff"));
                iconColor = a.getColor(R.styleable.MaterialTabHost_advtabs_iconColor, Color.WHITE);
                textColor = a.getColor(R.styleable.MaterialTabHost_advtabs_textColor, Color.WHITE);
                int defaultFixTabsLimit = getResources().getInteger(R.integer.defaultNonFixTabsCountStart);
                fixtablimit = a.getColor(R.styleable.MaterialTabHost_advtabs_nonFixTabsCountStart, defaultFixTabsLimit);
                custom_tab_layout_id = a.getResourceId(R.styleable.MaterialTabHost_advtabs_customTabLayout, -1);


            } finally {
                a.recycle();
            }
        } else {
            hasIcons = false;
        }

        this.isInEditMode();
        scrollable = false;
        isTablet = this.getResources().getBoolean(R.bool.isTablet);
        density = this.getResources().getDisplayMetrics().density;

        // setOverScrollMode(SCROLL_AXIS_NONE);
        tabSelected = 0;
        // initialize tabs list
        tabs = new LinkedList<MaterialTab>();
        // set background color
        super.setBackgroundColor(primaryColor);

    }

    /**
     * this will only work if you have setup the layout attribute in the xml component
     * please also check if you have put layout reference id in the xml @customTabLayout
     *
     * @param label_text
     * @return
     */
    public MaterialTab createCustomTextTab(String label_text) {
        final MaterialTab mattab = new MaterialTab(this.getContext(),
                new tabBuilder(tabBuilder.layout.TAB_CUSTOM_TEXT)
                        .with(getContext())
                        .setLayout(custom_tab_layout_id)
                        .initInstance());
        mattab.setText(label_text);
        return mattab;
    }

    /**
     * programmatically select the layout id and have it reference in the runtime
     *
     * @param custom_tab_layout_id
     * @param label_text
     * @return
     */
    public MaterialTab createCustomTextTab(int custom_tab_layout_id, String label_text, boolean withBubble) {
        final MaterialTab mattab = new MaterialTab(this.getContext(),
                new tabBuilder(withBubble ? tabBuilder.layout.TAB_CUSTOM_TEXT : tabBuilder.layout.TAB_CUSTOM_NO_BUBBLE)
                        .with(getContext())
                        .setLayout(custom_tab_layout_id)
                        .initInstance());
        mattab.setText(label_text);
        return mattab;
    }

    /**
     * initialize the interactive tab during the run time
     *
     * @param label_text
     * @return
     */
    public MaterialTab createInteractiveTab(String label_text) {
        final MaterialTab mattab = new MaterialTab(this.getContext(),
                new tabBuilder(tabBuilder.layout.TAB_MATERIAL)
                        .with(getContext())
                        .initInstance());
        mattab.setText(label_text);
        return mattab;
    }

    /**
     * just a classic material tab for
     *
     * @param label_text
     * @return
     */
    public MaterialTab createInteractiveTab(CharSequence label_text) {
        final MaterialTab mattab = new MaterialTab(this.getContext(),
                new tabBuilder(tabBuilder.layout.TAB_MATERIAL)
                        .with(getContext())
                        .initInstance());
        mattab.setText(label_text);
        return mattab;
    }

    /**
     * every thing with the default design
     *
     * @param label_text
     * @return
     */
    public MaterialTab createTabText(String label_text) {
        final MaterialTab mattab = new MaterialTab(this.getContext(),
                new tabBuilder(tabBuilder.layout.TAB_CLASSIC).with(getContext()).initInstance());
        mattab.setText(label_text);
        return mattab;
    }

    public MaterialTab createTab(tabBuilder n) {
        return new MaterialTab(this.getContext(), n);
    }

    /**
     * added by Hesk for dynamically setting the tab sizes.
     *
     * @param n
     */
    public void setFixTabLimit(int n) {
        fixtablimit = n;
        scrollable = false;
        if (tabs.size() > n && !hasIcons) {
            scrollable = true;
        }
        if (tabs.size() > 6 && hasIcons) {
            scrollable = true;
        }
        notifyDataSetChanged();
    }

    public void setBorder(int borderWidth, int color) {
        borderEdge = true;
        borderwidth = borderWidth;
        borderColor = color;
    }

    public void setMeasurementType(int n) {
        fittype = n;
    }

    @SuppressLint("ResourceAsColor")
    public void setBorderReferenceColor(int borderWidth, int ResId) {
        borderEdge = true;
        borderwidth = borderWidth;
        borderColor = layout.getContext().getResources().getColor(ResId);
    }

    public void setPrimaryColor(int color) {
        this.primaryColor = color;

        this.setBackgroundColor(primaryColor);

        for (MaterialTab tab : tabs) {
            tab.setPrimaryColor(color);
        }
    }

    public void setCustomBackground(int resId) {
        super.setBackground(getContext().getResources().getDrawable(resId));
    }

    public void setAccentColor(int color) {
        this.accentColor = color;

        for (MaterialTab tab : tabs) {
            tab.setAccentColor(color);
        }
    }

    public void setTextColor(int color) {
        this.textColor = color;

        for (MaterialTab tab : tabs) {
            tab.setTextColor(color);
        }
    }

    public void setIconColor(int color) {
        this.iconColor = color;

        for (MaterialTab tab : tabs) {
            tab.setIconColor(color);
        }
    }

    public void addTab(MaterialTab tab) {
        // add properties to tab
        tab.setAccentColor(accentColor);
        tab.setPrimaryColor(primaryColor);
        tab.setTextColor(textColor);
        tab.setIconColor(iconColor);
        tab.setPosition(tabs.size());

        // insert new tab in list
        tabs.add(tab);

        if (tabs.size() == fixtablimit && !hasIcons) {
            // switch tabs to scrollable before its draw
            scrollable = true;
        }

        if (tabs.size() == 6 && hasIcons) {
            scrollable = true;
        }
    }


    public void setSelectedNavigationItem(int position) {
        if (position < 0 || position > tabs.size()) {
            throw new RuntimeException("Index overflow");
        } else {
            // tab at position will select, other will deselect
            for (int i = 0; i < tabs.size(); i++) {
                MaterialTab tab = tabs.get(i);

                if (i == position) {
                    tab.activateTab();
                } else {
                    tabs.get(i).disableTab();
                }
            }

            // move the tab if it is slidable
            if (scrollable) {
                scrollTo(position);
            }

            tabSelected = position;
        }

    }

    private void scrollTo(int position) {
        int totalWidth = 0;//(int) ( 60 * density);
        for (int i = 0; i < position; i++) {
            int width = tabs.get(i).getView().getWidth();
            if (width == 0) {
                if (!isTablet)
                    width = (int) (tabs.get(i).getTabMinWidth() + (24 * density));
                else
                    width = (int) (tabs.get(i).getTabMinWidth() + (48 * density));
            }

            totalWidth += width;
        }
        scrollView.smoothScrollTo(totalWidth, 0);
    }

    @Override
    public void removeAllViews() {
        for (int i = 0; i < tabs.size(); i++) {
            tabs.remove(i);
        }
        layout.removeAllViews();
        super.removeAllViews();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.getWidth() != 0 && tabs.size() != 0)
            notifyDataSetChanged();
    }


    private View prepareBorderView() {

        LinearLayout.LayoutParams separator = new LinearLayout.LayoutParams((int) (borderwidth * density), HorizontalScrollView.LayoutParams.MATCH_PARENT);
        //border set
        View borderVertical = new View(layout.getContext());
        borderVertical.setBackgroundColor(borderColor);
        borderVertical.setLayoutParams(separator);

        return borderVertical;
    }

    /**
     * set to reinitialize the tab view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void notifyDataSetChanged() {
        super.removeAllViews();
        layout.removeAllViews();


        final Point s = new Point();
        this.getDisplay().getSize(s);
        int screen_width = s.x;


        if (!scrollable) { // not scrollable tabs

            int tabWidth = screen_width / tabs.size() - (borderEdge ? (int) (borderwidth * density) : 0);
            // set params for resizing tabs width
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tabWidth, HorizontalScrollView.LayoutParams.MATCH_PARENT);
            for (int i = 0; i < tabs.size(); i++) {
                MaterialTab t = tabs.get(i);
                if (i == 0 && borderEdge) {
                    // first tab
                    layout.addView(prepareBorderView());
                }
                layout.addView(t.getView(), params);
                if (borderEdge)
                    layout.addView(prepareBorderView());

            }
        } else { //scrollable tabs

            if (!isTablet) {
                for (int i = 0; i < tabs.size(); i++) {
                    LinearLayout.LayoutParams params;
                    MaterialTab tab = tabs.get(i);

                    int tabWidth = (int) (tab.getTabMinWidth() + (24 * density)); // 12dp + text/icon width + 12dp

                    if (i == 0) {

                        // first tab
                        View view = new View(layout.getContext());
                        view.setMinimumWidth((int) (60 * density));
                        layout.addView(view);
                    }

                    params = new LinearLayout.LayoutParams(tabWidth, HorizontalScrollView.LayoutParams.MATCH_PARENT);
                    //add tha tab
                    layout.addView(tab.getView(), params);


                    //add separator
                    if (borderEdge && i < tabs.size() - 1) {
                        layout.addView(prepareBorderView());
                    }


                    //add separator
                    if (i == tabs.size() - 1) {
                        // last tab
                        View view = new View(layout.getContext());
                        view.setMinimumWidth((int) (60 * density));
                        layout.addView(view);
                    }
                }
            } else {
                // is a tablet
                for (int i = 0; i < tabs.size(); i++) {
                    LinearLayout.LayoutParams params;
                    MaterialTab tab = tabs.get(i);

                    int tabWidth = (int) (tab.getTabMinWidth() + (48 * density)); // 24dp + text/icon width + 24dp

                    params = new LinearLayout.LayoutParams(tabWidth, HorizontalScrollView.LayoutParams.MATCH_PARENT);
                    layout.addView(tab.getView(), params);
                }
            }
        }

        if (isTablet && scrollable) {
            // if device is a tablet and have scrollable tabs add right and left arrows
            Resources res = getResources();

            left = new ImageButton(this.getContext());
            left.setId(R.id.left);
            left.setImageDrawable(res.getDrawable(R.drawable.left_arrow));
            left.setBackgroundColor(Color.TRANSPARENT);
            left.setOnClickListener(this);

            // set 56 dp width and 48 dp height
            RelativeLayout.LayoutParams paramsLeft = new LayoutParams((int) (56 * density), (int) (48 * density));
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            this.addView(left, paramsLeft);

            right = new ImageButton(this.getContext());
            right.setId(R.id.right);
            right.setImageDrawable(res.getDrawable(R.drawable.right_arrow));
            right.setBackgroundColor(Color.TRANSPARENT);
            right.setOnClickListener(this);

            RelativeLayout.LayoutParams paramsRight = new LayoutParams((int) (56 * density), (int) (48 * density));
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            paramsRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            this.addView(right, paramsRight);

            RelativeLayout.LayoutParams paramsScroll = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            paramsScroll.addRule(RelativeLayout.LEFT_OF, R.id.right);
            paramsScroll.addRule(RelativeLayout.RIGHT_OF, R.id.left);
            this.addView(scrollView, paramsScroll);
        } else {
            // if is not a tablet add only scrollable content
            RelativeLayout.LayoutParams paramsScroll = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            this.addView(scrollView, paramsScroll);
        }

        this.setSelectedNavigationItem(tabSelected);
    }

    public MaterialTab getCurrentTab() {
        for (MaterialTab tab : tabs) {
            if (tab.isSelected())
                return tab;
        }

        return null;
    }

    @Override
    public void onClick(View v) { // on tablet left/right button clicked
        int currentPosition = this.getCurrentTab().getPosition();

        if (v.getId() == R.id.right && currentPosition < tabs.size() - 1) {
            currentPosition++;

            // set next tab selected
            this.setSelectedNavigationItem(currentPosition);

            // change fragment
            tabs.get(currentPosition).getTabListener().onTabSelected(tabs.get(currentPosition));
            return;
        }

        if (v.getId() == R.id.left && currentPosition > 0) {
            currentPosition--;

            // set previous tab selected
            this.setSelectedNavigationItem(currentPosition);
            // change fragment
            tabs.get(currentPosition).getTabListener().onTabSelected(tabs.get(currentPosition));
            return;
        }

    }
}
