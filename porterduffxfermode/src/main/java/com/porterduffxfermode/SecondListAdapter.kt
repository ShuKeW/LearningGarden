package com.porterduffxfermode

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *  @date 2019/1/24   4:36 PM
 *  @author weishukai
 *  @describe
 */
const val TYPE_ITEM = 0
const val TYPE_SWITCH = 1

class ItemBean {
    var type: Int = TYPE_ITEM
    var id: SettingType = SettingType.POINT_CARD
    var title: String = ""
}

enum class SettingType {
    POINT_CARD,//点卡余额
    ONLINE_SERVICE,//在线客服
    HELP_CENTER,//帮助中心
    ISSUE_FEEDBACK,//问题反馈
    VERSION,//版本号
    COMMUNITY,//加入社群
    RECOMMEND,//推荐给好友
    CHAT_ROOT,//聊天室
    FIAT_SWITCH,//法币切换
    EXCHANGE_RATE,//汇率概览
    RATE_DETAIL,//费率详情
    NIGHT_MODE//黑夜模式
}

fun getSystemIcon(type: SettingType, activity: Activity): Drawable {
    val resId = when (type) {
        SettingType.POINT_CARD -> R.drawable.icon_point_card
        SettingType.ONLINE_SERVICE -> R.drawable.icon_service
        SettingType.HELP_CENTER -> R.drawable.icon_mine_help
        SettingType.ISSUE_FEEDBACK -> R.drawable.icon_feedback
        SettingType.VERSION -> R.drawable.icon_update
        SettingType.COMMUNITY -> R.drawable.icon_community
        SettingType.RECOMMEND -> R.drawable.icon_recommend
        SettingType.CHAT_ROOT -> R.drawable.icon_chat
        SettingType.FIAT_SWITCH -> R.drawable.icon_fiat_to_token
        SettingType.EXCHANGE_RATE -> R.drawable.icon_exchange_rate
        SettingType.RATE_DETAIL -> R.drawable.icon_rate
        SettingType.NIGHT_MODE -> R.drawable.icon_night_mode
        else -> 0
    }
    return activity.resources.getDrawable(resId)
}

class SecondListAdapter(private var dataList: ArrayList<ItemBean>, private var secondActivity: SecondActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        Log.e("tag", "onCreateViewHolder")
        return when (p1) {
            TYPE_SWITCH -> {
                SwitchHolder(
                    LayoutInflater.from(secondActivity).inflate(R.layout.item_list_switch, p0, false),
                    secondActivity
                )
            }
            else -> ItemHolder(
                LayoutInflater.from(secondActivity).inflate(R.layout.item_list, p0, false),
                secondActivity
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        Log.e("tag", "onBindViewHolder:    $p1")
        if (p0 is ItemHolder) {
            p0.onBind(dataList[p1])
        } else if (p0 is SwitchHolder) {
            p0.onBind(dataList[p1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }
}

class ItemHolder(itemView: View, private var secondActivity: SecondActivity) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    fun onBind(itemBean: ItemBean) {
        Log.e("tag", "onBindViewHolder:    ${itemBean.title}")
//        itemView.setBackgroundResource(R.color.white_background_color)
        val iconDrawable = getSystemIcon(itemBean.id, secondActivity)
        iconDrawable.setBounds(0, 0, iconDrawable.minimumWidth, iconDrawable.minimumHeight)
        titleView.setCompoundDrawables(iconDrawable, null, null, null)
        titleView.text = itemBean.title
    }
}

class SwitchHolder(itemView: View, private var secondActivity: SecondActivity) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val switchButton: UntouchableSwitchCompat = itemView.findViewById(R.id.switchNightMode)

    init {
        switchButton.setOnClickListener(this)
    }

    fun onBind(itemBean: ItemBean) {
        Log.e("tag", "onBindViewHolder:    ${itemBean.title}")
//        itemView.setBackgroundResource(R.color.white_background_color)
        titleView.text = itemBean.title
        val iconDrawable = getSystemIcon(itemBean.id, secondActivity)
        iconDrawable.setBounds(0, 0, iconDrawable.minimumWidth, iconDrawable.minimumHeight)
        titleView.setCompoundDrawables(iconDrawable, null, null, null)
//        switchButton.isChecked = secondActivity.isNightMode
        switchButton.isChecked = getNightModeIsOpen()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.switchNightMode -> {
                secondActivity.changeNightMode(v)
            }
        }
    }

}