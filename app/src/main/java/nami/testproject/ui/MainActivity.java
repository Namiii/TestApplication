package nami.testproject.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.TextView;

import java.util.Locale;

import nami.testproject.R;


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mSearchPhraseTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.activity_main_custom_textview);
        mSearchPhraseTextView = (TextView) findViewById(R.id.activity_main_search_phrase_textview);
        String text = "Nadini Bali Jungle Resort and Spa Ubud ubud bali bali bali UBUD";
        String searchPhrase = "balI . UBUD";
        mTextView.setText(getBoldString(searchPhrase, text));
        mSearchPhraseTextView.setText(searchPhrase);
    }


    private Spannable getBoldString(String searchPhrase, String text){
        Spannable boldPhrase = new SpannableString(text);
        String[] searchTerms = searchPhrase.split("\\s*(\\s|\\.|,)\\s*");
        if(searchTerms.length > 0){
            for(String searchTerm: searchTerms){
                boldText(searchTerm, text, boldPhrase);
            }
        }

        return boldPhrase;
    }

    private void boldText(String searchTerm, String text, Spannable finalText){
        if (text.toLowerCase().contains(searchTerm.toLowerCase())) {
            int startPos = text.toLowerCase(Locale.US).indexOf(searchTerm.toLowerCase(Locale.US));
            int endPos = startPos + searchTerm.length();
            finalText.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
