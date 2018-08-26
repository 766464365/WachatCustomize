package com.xuwei.framework.popuppicker.entity;

import java.util.List;

/**
 * 用于联动选择器展示的第一级条目
 *
 * @author xw 2018/6/7
 */
public interface LinkageFirst<Snd> extends LinkageItem {

    List<Snd> getSeconds();

}