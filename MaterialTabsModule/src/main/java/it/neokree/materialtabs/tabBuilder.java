package it.neokree.materialtabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import at.markushi.ui.RevealColorView;

/**
 * enhanced ui and layout for further developments
 * <p/>
 * Created by hesk on 27/3/15.
 */
public class tabBuilder<T extends TextView> {
    public static enum layout {
        TAB_ICON, TAB_CLASSIC, TAB_MATERIAL, TAB_MATERIAL_ICON, TAB_CUSTOM_TEXT, TAB_CUSTOM_ICON,

        //@todo will need to think about how to implement LTR and RTL orientations
        TAB_HORIZONTAL_TEXT_ICON_LTR,
        TAB_HORIZONTAL_TEXT_ICON_RTL
    }

    private Context ctx;
    private layout type;
    private View completeView;
    private ImageView icon;
    private T text;
    private RevealColorView background;
    private ImageView selector;
    private int custom_layout_id;
    private boolean hasIcon;

    public tabBuilder(layout type_layout_configuration) {
        type = type_layout_configuration;
    }

    public tabBuilder with(Context f) {
        ctx = f;
        return this;
    }

    public tabBuilder setLayout(int ResId) {
        custom_layout_id = ResId;
        return this;
    }

    @SuppressLint("WrongViewCast")
    public tabBuilder initInstance() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (type == layout.TAB_MATERIAL_ICON) type = layout.TAB_ICON;
            if (type == layout.TAB_MATERIAL) type = layout.TAB_CLASSIC;
        }

        switch (type) {
            case TAB_CLASSIC:
                completeView = LayoutInflater.from(ctx).inflate(R.layout.tab, null);
                text = (T) completeView.findViewById(R.id.text);
                hasIcon = false;
                break;

            case TAB_ICON:
                completeView = LayoutInflater.from(ctx).inflate(R.layout.tab_icon, null);
                icon = (ImageView) completeView.findViewById(R.id.icon);
                hasIcon = true;
                break;

            case TAB_MATERIAL:
                completeView = LayoutInflater.from(ctx).inflate(R.layout.material_tab, null);
                text = (T) completeView.findViewById(R.id.text);
                background = (RevealColorView) completeView.findViewById(R.id.reveal);
                hasIcon = false;
                break;

            case TAB_MATERIAL_ICON:
                completeView = LayoutInflater.from(ctx).inflate(R.layout.material_tab_icon, null);
                icon = (ImageView) completeView.findViewById(R.id.icon);
                background = (RevealColorView) completeView.findViewById(R.id.reveal);
                hasIcon = true;
                break;

            case TAB_CUSTOM_TEXT:
                completeView = LayoutInflater.from(ctx).inflate(custom_layout_id, null);
                text = (T) completeView.findViewById(R.id.text);
                background = (RevealColorView) completeView.findViewById(R.id.reveal);
                hasIcon = false;
                break;

            case TAB_CUSTOM_ICON:
                completeView = LayoutInflater.from(ctx).inflate(custom_layout_id, null);
                icon = (ImageView) completeView.findViewById(R.id.icon);
                background = (RevealColorView) completeView.findViewById(R.id.reveal);
                hasIcon = true;
                break;

            default:
                completeView = LayoutInflater.from(ctx).inflate(R.layout.tab, null);
                text = (T) completeView.findViewById(R.id.text);
                hasIcon = false;

                break;
        }

        selector = (ImageView) completeView.findViewById(R.id.selector);
        return this;
    }

    /**
     * get the whole tab area in layout view
     *
     * @return
     */

    public View wholeTab() {
        return completeView;
    }

    /**
     * to determine if the icon is exist or not
     *
     * @return
     */
    public boolean hasIcon() {
        return hasIcon;
    }

    /**
     * get all text view based component for further settings
     *
     * @return
     */
    public T getTextView() {
        return text;
    }

    /**
     * get all the icon related image view
     *
     * @return
     */
    public ImageView getIcon() {
        return icon;
    }

    /**
     * the lower selection bar on the tab
     *
     * @return
     */
    public ImageView getSelector() {
        return selector;
    }

    /**
     * the interactive panel for the touch area.
     *
     * @return
     */
    public RevealColorView getBg() {
        return background;
    }

}
