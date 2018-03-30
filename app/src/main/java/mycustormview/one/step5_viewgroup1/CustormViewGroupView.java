package mycustormview.one.step5_viewgroup1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by koudai_nick on 2017/12/14.
 * 功能:自定义viewgroup的基础功能
 */

public class CustormViewGroupView extends ViewGroup{


    public CustormViewGroupView(Context context) {
        super(context);
    }

    public CustormViewGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustormViewGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


     // 该viewgroup 支持margin
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        // 为其所有的孩子设置宽和高,这段代码执行以后,childview的宽和高都已经正确的计算过了
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int finalWidth;
        int finalHeight;
        if(widthMode == MeasureSpec.EXACTLY){
            finalWidth = sizeWidth;
        } else {
            // 计算内部view的大小,然后再进行处理
            int childCount= getChildCount();
            int tWidth =0; //上面两个view的宽度
            int bWidth = 0;//底部两个view的宽度

            int tHeight = 0; //上面两个view的高度
            int bHeight = 0; //下面两个view的高度

            for(int i=0;i<childCount;i++){
                View childView = getChildAt(i);
                //获得子view的大小和margin
                int childViewWidth = childView.getMeasuredWidth();
                int childViewHeight = childView.getMeasuredHeight();
                MarginLayoutParams childParams = (MarginLayoutParams) childView.getLayoutParams();

                // 对于上面两个childview的处理
                if(i==0||i==1){
                    tWidth += childViewWidth + childParams.leftMargin + childParams.rightMargin;
                }
                if(i==0 || i==1){
                    tHeight += childViewHeight + childParams.topMargin + childParams.bottomMargin;
                }
                if(i==2||i==3){
                    bWidth +=  childViewWidth + childParams.leftMargin + childParams.rightMargin;
                }
                if(i==2||i==3){
                    bHeight +=childViewHeight + childParams.topMargin + childParams.bottomMargin;
                }
            }

            finalWidth = Math.max(tWidth,bWidth);
            finalHeight = Math.max(tHeight,bHeight);
        }


        if(heightMode == MeasureSpec.EXACTLY){
            finalHeight = sizeHeight;
        } else {
            // 计算内部view的大小,然后再进行处理
            int childCount= getChildCount();
            int tWidth =0; //上面两个view的宽度
            int bWidth = 0;//底部两个view的宽度

            int tHeight = 0; //上面两个view的高度
            int bHeight = 0; //下面两个view的高度

            for(int i=0;i<childCount;i++){
                View childView = getChildAt(i);
                //获得子view的大小和margin
                int childViewWidth = childView.getMeasuredWidth();
                int childViewHeight = childView.getMeasuredHeight();
                MarginLayoutParams childParams = (MarginLayoutParams) childView.getLayoutParams();

                // 对于上面两个childview的处理
                if(i==0||i==1){
                    tWidth += childViewWidth + childParams.leftMargin + childParams.rightMargin;
                }
                if(i==0 || i==1){
                    tHeight += childViewHeight + childParams.topMargin + childParams.bottomMargin;
                }
                if(i==2||i==3){
                    bWidth +=  childViewWidth + childParams.leftMargin + childParams.rightMargin;
                }
                if(i==2||i==3){
                    bHeight +=childViewHeight + childParams.topMargin + childParams.bottomMargin;
                }
            }

            finalWidth = Math.max(tWidth,bWidth);
            finalHeight = Math.max(tHeight,bHeight);
        }



        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension(finalWidth,finalHeight);
    }
    // 设置childview的绘制区域
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childview根据其宽和搞，以及margin来进行布局
         */
        for(int j=0;j<cCount;j++){
            View childView = getChildAt(j);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0,ct = 0,cr = 0,cb = 0;
            switch (j){
                case 0:
                    // 最左边的那个view
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 1:
                    // 最右边的那个view
                    cl = getWidth() - cWidth -  cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 2:
                    // 左边下方的那个view
                    cl = cParams.leftMargin;
                    ct = getHeight() - cParams.bottomMargin -cHeight;
                    break;
                case 3:
                    // 右边下发的那个view
                    cl = getWidth() - cWidth -  cParams.leftMargin;
                    ct = getHeight() - cParams.bottomMargin - cHeight;
                    break;

            }
            cr = cl + cWidth;
            cb = ct + cHeight;
            childView.layout(cl,ct,cr,cb);
        }
    }
}
