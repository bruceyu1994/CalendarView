package com.bruceyu.library;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BruceYu on 2017/4/7 20:24.
 * Email: 13738725570@163.com
 * Description: 日期选择器的初始化参数类
 */

public class DataModel implements Serializable {
// TYPE_MULTI：多选，TYPE_RANGE：范围选，TYPE_ONLY_READ：只读
//        public enum TYPE {TYPE_MULTI, TYPE_RANGE, TYPE_ONLY_READ}

    //        TYPE type;                                       // 类型
    public int yearStart;                                      // 日历开始的年份
    public int monthStart;                                     // 日历开始的月份
    public int yearEnd;                                      // 日历结束的年份
    public int monthEnd;                                     // 日历结束的月份
    public int yearCurrent;                                      // 日历当前的年份
    public int monthCurrent;                                     // 日历当前的月份
    //        public int monthCount;                                     // 要显示几个月
    public List<CalendarDay> invalidDays;   // 无效的日期
    public List<CalendarDay> busyDays;      // 被占用的日期
    public SelectedDays<CalendarDay> selectedDays;  // 默认选择的日期
    public int leastDaysNum;                                   // 至少选择几天
    public int mostDaysNum;                                    // 最多选择几天
    public List<CalendarDay> tags;          // 日期下面对应的标签
    public String defTag;                                      // 默认显示的标签
//        public boolean displayTag;                               // 是否显示标签
}