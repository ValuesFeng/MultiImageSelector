package me.nereo.multi_image_selector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Builder for crop Intents and utils for handling result
 */
public class Image {

    /** 单选 */
    public static final int MODE_SINGLE = 0;
    /** 多选 */
    public static final int MODE_MULTI = 1;
    /** 选择结果，返回为 ArrayList<String>图片路径集合  */
    public static final String EXTRA_RESULT = "select_result";

    public static final int REQUEST_IMAGE = 6709;

    private ArrayList<String> mSelectPath;

    public static interface Extra {
        /** 最大图片选择次数，int类型，默认9 */
        String EXTRA_SELECT_COUNT = "max_select_count";
        /** 图片选择模式，默认多选 */
        String EXTRA_SELECT_MODE = "select_count_mode";
        /** 是否显示相机，默认显示 */
        String EXTRA_SHOW_CAMERA = "show_camera";
        /** 默认选择集 */
        String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    }

    public Image() {
        imageIntent = new Intent();
    }

    private Intent imageIntent;

    /**
     * whether open camera
     * @param isShow
     * @return
     */
    public Image openCamera(boolean isShow){
        imageIntent.putExtra(Extra.EXTRA_SHOW_CAMERA, isShow);
        return this;
    }

    /**
     * select max count
     * @param count
     * @return
     */
    public Image maxCount(int count){
        imageIntent.putExtra(Extra.EXTRA_SELECT_COUNT,count);
        return this;
    }

    /**
     * select image mode
     * @param mode
     * @return
     */
    public Image selectMode(int mode){
        imageIntent.putExtra(Extra.EXTRA_SELECT_MODE,mode);
        return this;
    }

    public Image putSelectedPath(ArrayList<String> list){
        imageIntent.putExtra(Extra.EXTRA_DEFAULT_SELECTED_LIST,list);
        return this;
    }

    /**
     * Send the crop Intent!
     *
     * @param activity Activity that will receive result
     */
    public void start(Activity activity) {
        activity.startActivityForResult(getIntent(activity), REQUEST_IMAGE);
    }

    /**
     * Send the crop Intent!
     *
     * @param context  Context
     * @param fragment Fragment that will receive result
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void start(Context context, Fragment fragment) {
        fragment.startActivityForResult(getIntent(context), REQUEST_IMAGE);
    }

    /**
     * Send the crop Intent!
     *
     * @param context  Context
     * @param fragment Fragment that will receive result
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void start(Context context, android.support.v4.app.Fragment fragment) {
        fragment.startActivityForResult(getIntent(context), REQUEST_IMAGE);
    }


    @VisibleForTesting
    Intent getIntent(Context context) {
        if (mSelectPath==null){
            mSelectPath = new ArrayList<>();
        }
        imageIntent.setClass(context, MultiImageSelectorActivity.class);
        return imageIntent;
    }

}
