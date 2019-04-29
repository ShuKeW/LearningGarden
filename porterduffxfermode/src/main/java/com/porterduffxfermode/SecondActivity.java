package com.porterduffxfermode;

import android.app.UiModeManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.porterduffxfermode.NightModeUtilKt.closeNightMode;
import static com.porterduffxfermode.NightModeUtilKt.getNightModeIsOpen;
import static com.porterduffxfermode.NightModeUtilKt.openNightMode;
import static com.porterduffxfermode.SecondListAdapterKt.TYPE_ITEM;
import static com.porterduffxfermode.SecondListAdapterKt.TYPE_SWITCH;

/**
 * @author weishukai
 * @date 2019/1/23   11:14 AM
 * @describe
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "SecondActivity";
    private ConstraintLayout rootLayout;
//    private TextView text;
//    private UntouchableSwitchCompat switchColor;

    private RecyclerView recyclerView;
    private SecondListAdapter adapter;
    private SettingType[] ids = new SettingType[]{SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.POINT_CARD,//点卡余额
            SettingType.ONLINE_SERVICE,//在线客服
            SettingType.HELP_CENTER,//帮助中心
            SettingType.ISSUE_FEEDBACK,//问题反馈
            SettingType.VERSION,//版本号
            SettingType.COMMUNITY,//加入社群
            SettingType.RECOMMEND,//推荐给好友
            SettingType.CHAT_ROOT,//聊天室
            SettingType.FIAT_SWITCH,//法币切换
            SettingType.EXCHANGE_RATE,//汇率概览
            SettingType.RATE_DETAIL,//费率详情
            SettingType.NIGHT_MODE//黑夜模式
    };

    private NightModeChangeAnimView nightModeChangeAnim;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_second);
        rootLayout = findViewById(R.id.root_layout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<ItemBean> dataList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ItemBean item = new ItemBean();
            item.setTitle("标题" + i);
            item.setId(ids[i]);
            if (i == ids.length - 1) {
                item.setType(TYPE_SWITCH);
            } else {
                item.setType(TYPE_ITEM);
            }
            dataList.add(item);
        }
        adapter = new SecondListAdapter(dataList, this);
        recyclerView.setAdapter(adapter);

//        text = findViewById(R.id.text_content);
//        switchColor = findViewById(R.id.switch_color);
//        switchColor.setOnClickListener(this);

//        if (Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(this)) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivityForResult(intent, 100);
//            } else {
//            }
//        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.switch_color:
//                Log.e("NightModeChangeAnimView", "onTouchEvent");
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
////                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 111);
////                }
//                if (switchColor.isChecked()) {
//                    switchColor.setChecked(false);
//                    closeNightMode();
//                    startChangeAnim(false);
//                } else {
//                    switchColor.setChecked(true);
//                    openNightMode();
//                    startChangeAnim(true);
//                }
//                break;
        }
    }

    private void startChangeAnim(boolean isOpen) {
//        if (nightModeChangeAnim == null) {
//            nightModeChangeAnim = new NightModeChangeAnimView(this);
//        }
//        nightModeChangeAnim.init(this, switchColor);
//        nightModeChangeAnim.updateBackground();
//        nightModeChangeAnim.attachToRootView();
//        if (isOpen) {
//            rootLayout.setBackgroundColor(Color.parseColor("#171B30"));
//            text.setTextColor(Color.parseColor("#FFFFFF"));
//        } else {
//            rootLayout.setBackgroundColor(Color.parseColor("#F7F8FA"));
//            text.setTextColor(Color.parseColor("#333333"));
//        }
//        nightModeChangeAnim.startAnim();
    }

    public void changeNightMode(@NotNull View v) {
        if (nightModeChangeAnim == null) {
            nightModeChangeAnim = new NightModeChangeAnimView(this);
        }
        nightModeChangeAnim.init(this, v);
        nightModeChangeAnim.updateBackground();
        nightModeChangeAnim.attachToRootView();

        boolean isOpen = getNightModeIsOpen();
//        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
//        boolean isOpen = uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
        DisplayMetrics newMetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (isOpen) {
            closeNightMode();
            configuration.uiMode = Configuration.UI_MODE_NIGHT_NO;
            changeConfig(configuration, newMetrics);
//            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
//            rootLayout.setBackgroundColor(Color.parseColor("#F7F8FA"));
            rootLayout.setBackgroundColor(getResources().getColor(R.color.window_background_color));
        } else {
            openNightMode();
            configuration.uiMode = Configuration.UI_MODE_NIGHT_YES;
            changeConfig(configuration, newMetrics);
//            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
//            rootLayout.setBackgroundColor(Color.parseColor("#171B30"));
            rootLayout.setBackgroundColor(getResources().getColor(R.color.window_background_color));
        }
//        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        for (int i = linearLayoutManager.findFirstVisibleItemPosition(); i <= linearLayoutManager.findLastVisibleItemPosition(); i++) {
//            adapter.notifyItemChanged(i);
//        }
        for (int i = 0; i < adapter.getItemCount(); i++) {
            adapter.notifyItemChanged(i);
        }
//        adapter.notifyDataSetChanged();
        nightModeChangeAnim.startAnim();
    }

    private void changeConfig(Configuration newConfig, DisplayMetrics newMetrics) {
        if (getWindow() != null) {
            // Pass the configuration changed event to the window
            getWindow().onConfigurationChanged(newConfig);
        }
        getDelegate().onConfigurationChanged(newConfig);
        getResources().updateConfiguration(newConfig, newMetrics);
    }

    public boolean isNightMode() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        boolean isOpen = uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
        return isOpen;
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 100) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (!Settings.canDrawOverlays(this)) {
//                    // SYSTEM_ALERT_WINDOW permission not granted...
//                    Toast.makeText(this, "not granted", Toast.LENGTH_LONG);
//                }
//            }
//        }
//    }
}
