package com.xuwei.framework.popuppicker.entity;

/**
 * 用于联动选择器展示的条目
 *
 * @author xw 2018/6/7
 */
interface LinkageItem extends WheelItem {

    /**
     * 唯一标识，用于判断两个条目是否相同
     */
    Object getId();

}
