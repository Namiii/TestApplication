package nami.testproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import nami.testproject.R;
import nami.testproject.models.RoomModel;

import static nami.testproject.R.string.total;

public class RoomsActivity extends AppCompatActivity {
    private ArrayList<RoomModel> rooms;
    private LinearLayout roomsContainer;
    private static final String ROOM_NIGHT_TEXT = "1 room * 1 night";
    private static final StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity_layout);
        roomsContainer = (LinearLayout) findViewById(R.id.rooms_container);
        setTitle("Room Types");
        createDummyData();
        displayRooms();
    }

    private void displayRooms() {
        if (roomsContainer.getChildCount() > 0) {
            roomsContainer.removeAllViews();
        }

        View infoView = getLayoutInflater().inflate(R.layout.info_cell, roomsContainer, false);
        roomsContainer.addView(infoView);


        for (final RoomModel roomModel : rooms) {
            View rootView = getLayoutInflater().inflate(R.layout.room_cell_6, roomsContainer, false);
            TextView roomTitle = (TextView) rootView.findViewById(R.id.room_name_tv);
            TextView roomPrice = (TextView) rootView.findViewById(R.id.room_price_tv);
            LinearLayout notesContainer = (LinearLayout) rootView.findViewById(R.id.room_details_container);
            TextView roomLeftCount = (TextView) rootView.findViewById(R.id.room_count_tv);
            Button roomSelectButton = (Button) rootView.findViewById(R.id.room_select_btn);
            TextView roomNight = (TextView) rootView.findViewById(R.id.room_night_tv);
            TextView totalLabel = (TextView) rootView.findViewById(R.id.room_price_label_tv);
            TextView barPrice = (TextView) rootView.findViewById(R.id.room_bar_price_tv);

            roomTitle.setText(roomModel.name);
            String roomsLeftText = getString(R.string.rooms_left) + " " + roomModel.roomsLeft;
            roomLeftCount.setText(roomsLeftText);
            displayNotes(notesContainer, roomModel);
            String roomPriceText;
            if (totalLabel != null) {
                roomPriceText = roomModel.price;

            } else {
                roomPriceText = getString(total) + " " + roomModel.price;
            }

            if (barPrice != null) {
                setStrikeThrough(barPrice, roomModel.price);
            }
            roomPrice.setText(roomPriceText);
            if (roomNight != null) {
                roomNight.setText(getRoomNightText());
            }

            roomSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(RoomsActivity.this, "Boo Yaaah", Toast.LENGTH_SHORT).show();
                }
            });

            roomsContainer.addView(rootView);
        }
    }

    private void displayNotes(LinearLayout notesContainer, RoomModel roomModel) {
        for (String note : roomModel.notes) {
            View rootView = getLayoutInflater().inflate(R.layout.room_note_layout, notesContainer, false);
            TextView noteTV = (TextView) rootView.findViewById(R.id.room_details_tv);
            if (note.equals("Breakfast Included")) {
                noteTV.setTextColor(ContextCompat.getColor(this, R.color.green));
            }
            String noteText = "\u2022 " + note;
            noteTV.setText(noteText);
            notesContainer.addView(rootView);
        }
    }

    private void createDummyData() {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }

        rooms.add(createRoom("Normal Room Normal Room Normal Room",
                "1200THB",
                "Breakfast excluded,This room can comfortably sleep two guests,no cancellation",
                5,
                true));

        rooms.add(createRoom("Normal Room",
                "1400THB",
                "Breakfast Included,This room can comfortably sleep two guests,no cancellation",
                2,
                false));

        rooms.add(createRoom("Deluxe Room",
                "2100THB",
                "Breakfast Included,This room can comfortably sleep three guests,cancel before 48 hours",
                3,
                false));

        rooms.add(createRoom("Master Suite",
                "8000THB",
                "Breakfast Included,This room can comfortably sleep five guests, no cancellation",
                1,
                false));
    }

    private RoomModel createRoom(String roomName, String roomPrice, String roomDescription, int roomsLeft, boolean isSelected) {
        RoomModel room = new RoomModel();
        room.name = roomName;
        room.price = roomPrice;
        room.notes = getDescriptionArray(roomDescription);
        room.roomsLeft = roomsLeft;
        room.isSelected = isSelected;
        return room;
    }

    private ArrayList<String> getDescriptionArray(String roomDescription) {
        ArrayList<String> notes = new ArrayList<>();
        String[] noteStrings = roomDescription.split(",");
        Collections.addAll(notes, noteStrings);
        return notes;
    }


    private String getRoomNightText() {
        return ROOM_NIGHT_TEXT;
    }

    private void setStrikeThrough(TextView tv, String text) {
        tv.setText(text, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) tv.getText();
        spannable.setSpan(STRIKE_THROUGH_SPAN, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
