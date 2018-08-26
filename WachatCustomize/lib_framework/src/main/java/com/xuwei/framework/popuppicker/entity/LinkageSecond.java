package com.xuwei.framework.popuppicker.entity;

import java.util.List;

/**
 * 用于联动选择器展示的第二级条目
 *
 * @author xw 2018/6/7
 */
public interface LinkageSecond<Trd> extends LinkageItem {

    List<Trd> getThirds();

}