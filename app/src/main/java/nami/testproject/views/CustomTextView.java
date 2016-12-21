package nami.testproject.views;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBoldSearchText(String text, String boldText) {
        if (text != null && !text.isEmpty() && boldText != null && !boldText.isEmpty()) {
            String tempText = text.toLowerCase();
            SpannableStringBuilder str = new SpannableStringBuilder(text);

            for (int i = -1; (i = tempText.indexOf(boldText, i + 1)) != -1; ) {
                setBoldText(str, i, i + boldText.length());
            }

            setText(str);
        }
    }

    private void setBoldText(SpannableStringBuilder str, int startIndex, int endIndex) {
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}