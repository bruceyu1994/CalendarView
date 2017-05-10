package com.bruceyu.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static com.bruceyu.library.SimpleMonthAdapter.MONTHS_IN_YEAR;

public class DayPickerView extends RecyclerView {
    protected Context mContext;
    protected SimpleMonthAdapter mAdapter;
    private DatePickerController mController;
    protected int mCurrentScrollState = 0;
    protected long mPreviousScrollPosition;
    protected int mPreviousScrollState = 0;
    private TypedArray typedArray;
    private OnScrollListener onScrollListener;
    private Calendar calendar;

    private DataModel dataModel;

    public DayPickerView(Context context) {
        this(context, null);
    }

    public DayPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.DayPickerView);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        init(context);
    }

    public void init(Context paramContext) {
        setLayoutManager(new LinearLayoutManager(paramContext));
        mContext = paramContext;
        setUpListView();

        onScrollListener = new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final SimpleMonthView child = (SimpleMonthView) recyclerView.getChildAt(0);
                if (child == null) {
                    return;
                }

                mPreviousScrollPosition = dy;
                mPreviousScrollState = mCurrentScrollState;
            }
        };
    }

    protected void setUpAdapter() {
        if (mAdapter == null) {
            mAdapter = new SimpleMonthAdapter(getContext(), typedArray, mController, dataModel);
            setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
    }

    protected void setUpListView() {
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(onScrollListener);
        setFadingEdgeLength(0);
    }

    /**
     * 设置参数
     *
     * @param mController 回调监听
     */
    public void setParameter(DatePickerController mController) {
        this.mController = mController;
        // 设置DataModel默认数据
        setDefaultDataModel();
        setUpAdapter();
        // 跳转到入住日期所在的月份
//        scrollToSelectedPosition(dataModel.selectedDays, dataModel.monthStart);
        //  跳转到当前年月
        scrollToMonth(dataModel.yearCurrent, dataModel.monthCurrent);
    }

    /**
     * 设置参数
     *
     * @param dataModel   数据
     * @param mController 回调监听
     */
    public void setParameter(DataModel dataModel, DatePickerController mController) {
        if (dataModel == null) {
            Toast.makeText(mContext, "DataModel初始数据不能为空", Toast.LENGTH_SHORT).show();
//            Log.e("crash", "请设置参数");
            return;
        }
        this.dataModel = dataModel;
        this.mController = mController;
        // 设置DataModel默认数据
        setDefaultDataModel();

        setUpAdapter();

        // 跳转到入住日期所在的月份
//        scrollToSelectedPosition(dataModel.selectedDays, dataModel.monthStart);
        //  跳转到当前年月
        scrollToMonth(dataModel.yearCurrent, dataModel.monthCurrent);
    }

    /**
     * 设置DataModel默认值，默认开始日期和结束日期相差6个月
     */
    private void setDefaultDataModel() {
        calendar = Calendar.getInstance();
        if (dataModel == null) {
            dataModel = new DataModel();
        }

        if (dataModel.invalidDays == null) {
            dataModel.invalidDays = new ArrayList<>();
        }

        if (dataModel.busyDays == null) {
            dataModel.busyDays = new ArrayList<>();
        }

        if (dataModel.tags == null) {
            dataModel.tags = new ArrayList<>();
        }

        if (dataModel.selectedDays == null) {
            dataModel.selectedDays = new SelectedDays<>();
        }

        if (dataModel.yearEnd <= 0) {
            dataModel.yearEnd = calendar.get(Calendar.YEAR);
        }

        if (dataModel.monthEnd <= 0) {
            dataModel.monthEnd = calendar.get(Calendar.MONTH);
        }

        if (dataModel.leastDaysNum <= 0) {
            dataModel.leastDaysNum = 0;
        }

        if (dataModel.mostDaysNum <= 0) {
            dataModel.mostDaysNum = 100;
        }

        if (dataModel.leastDaysNum > dataModel.mostDaysNum) {
            Log.e("error", "可选择的最小天数不能小于最大天数");
            throw new IllegalArgumentException("可选择的最小天数不能小于最大天数");
        }

        if (dataModel.defTag == null) {
            dataModel.defTag = "标签";
        }

        // 设置默认当前年月
        dataModel.yearCurrent = calendar.get(Calendar.YEAR);
        dataModel.monthCurrent = calendar.get(Calendar.MONTH);

        String strDate = dataModel.yearEnd + "-" + dataModel.monthEnd + "-"
                + calendar.get(Calendar.DAY_OF_MONTH);
        if (dataModel.yearStart <= 0) {
            dataModel.yearStart = DateUtils.getNewYear(strDate, -5 * 30 * 24 * 60 * 60);

        }

        if (dataModel.monthStart <= 0) {
            dataModel.monthStart = DateUtils.getNewMonth(strDate, -5 * 30 * 24 * 60 * 60);
        }
    }

    /**
     * 跳转到执行月年
     *
     * @param year
     * @param month
     */
    public void scrollToMonth(int year, int month) {
        if (mController != null && mAdapter != null) {
            int position = ((year - dataModel.yearStart) * MONTHS_IN_YEAR)
                    - dataModel.monthStart + dataModel.monthEnd;
            if (position > 0) {
                scrollToPosition(position);
            }
        } else {
            throw new UnsupportedOperationException("Do you specified a DatePickerController?");
        }
    }
}