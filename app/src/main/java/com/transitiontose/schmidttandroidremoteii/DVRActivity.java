package com.transitiontose.schmidttandroidremoteii;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.*;
import android.view.View.*;
import android.os.Bundle;
import android.widget.*;

// hi
// Terry Schmidt, CSC472, Fall 2015
// yo

public class DVRActivity extends Activity {

    private TextView DVRPowerText;
    private TextView stateText;
    private Button playButton;
    private Button stopButton;
    private Button pauseButton;
    private Button fastForwardButton;
    private Button rewindButton;
    private Button recordButton;
    private Button switchToTVButton;
    private Switch DVRPowerSwitch;
    private ButtonClickListener btnClick;
    private Boolean isInPlayMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvr);

        btnClick = new ButtonClickListener();
        DVRPowerText = (TextView) findViewById(R.id.DVRPowerText);
        stateText = (TextView) findViewById(R.id.stateText);
        playButton = (Button) findViewById(R.id.playButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        fastForwardButton = (Button) findViewById(R.id.fastForwardButton);
        rewindButton = (Button) findViewById(R.id.rewindButton);
        recordButton = (Button) findViewById(R.id.recordButton);
        switchToTVButton = (Button) findViewById(R.id.switchToTVButton);
        DVRPowerSwitch = (Switch) findViewById(R.id.DVRPowerSwitch);
        DVRPowerSwitch.setChecked(true);

        DVRPowerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    playButton.setClickable(true);
                    stopButton.setClickable(true);
                    pauseButton.setClickable(true);
                    fastForwardButton.setClickable(true);
                    rewindButton.setClickable(true);
                    recordButton.setClickable(true);
                    switchToTVButton.setClickable(true);
                    DVRPowerText.setText("On"); stateText.setText("Stopped");
                    isInPlayMode = false;
                } else {
                    playButton.setClickable(false); stopButton.setClickable(false); pauseButton.setClickable(false);
                    fastForwardButton.setClickable(false); rewindButton.setClickable(false); recordButton.setClickable(false);
                    switchToTVButton.setClickable(false);
                    DVRPowerText.setText("Off"); stateText.setText("Stopped");
                }
            }
        });

        int[] buttonIDlist = {
                R.id.playButton,
                R.id.stopButton,
                R.id.pauseButton,
                R.id.fastForwardButton,
                R.id.rewindButton,
                R.id.recordButton,
                R.id.switchToTVButton
        };

        for (int id : buttonIDlist) {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick); // set all relevant buttons as onClickListeners
        }
    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.playButton:  playPressed(); break; // when user presses play button, invoke corresponding method.
                case R.id.stopButton:  stopPressed(); break; // when user presses stop button, invoke corresponding method.
                case R.id.pauseButton: pausePressed(); break; // when user presses pause button, invoke corresponding method.
                case R.id.fastForwardButton:  fastForwardPressed(); break; // when user presses fast forward button, invoke corresponding method.
                case R.id.rewindButton: rewindPressed(); break; // when user presses rewind button, invoke corresponding method.
                case R.id.recordButton: recordPressed(); break; // when user presses record button, invoke corresponding method.
                case R.id.switchToTVButton: switchToTVPressed(); break; // when user presses switch to TV button, invoke corresponding method.
            }
        }
    }

    private void playPressed() { // check if DVR can be set to play.  If so, set state in UI, else impossibleRequest()
        if (stateText.getText() == "Recording") {
            impossibleRequest("Playing");
        } else {
            stateText.setText("Playing");
            isInPlayMode = true;
        }
    }

    private void stopPressed() { // stop the DVR.
        isInPlayMode = false;
        stateText.setText("Stopped");
    }

    private void pausePressed() { // check if DVR can be set to pause.  If so, set the state in UI.  Else call impossibleRequest().
        if (isInPlayMode == true && stateText.getText() != "Recording") {
            stateText.setText("Paused");
        } else {
            impossibleRequest("Paused");
        }
    }

    private void fastForwardPressed() { // check if DVR can be set to FF.  If so, set the state in UI, else call impossibleRequest().
        if (isInPlayMode == true && stateText.getText() != "Recording") {
            stateText.setText("Fast forwarding");
        } else {
            impossibleRequest("Fast forwarding");
        }
    }

    private void rewindPressed() { // check if DVR can be set to rewind.  If so, set the state, else call impossibleRequest().
        if (isInPlayMode == true && stateText.getText() != "Recording") {
            stateText.setText("Fast rewinding");
        } else {
            impossibleRequest("Fast rewinding");
        }
    }

    private void recordPressed() { // check if DVr can be set to record.  If so, set the state, else call impossibleRequest().
        if (stateText.getText() == "Stopped" && isInPlayMode == false) {
            stateText.setText("Recording");
        } else {
            impossibleRequest("Recording");
        }
    }

    private void switchToTVPressed() {
        finish();
    }

    private void impossibleRequest(String stateRequested) { // invoked when user requests an impossible instruction.  Displays toast notifying them.
        Toast.makeText(this, "You have selected an impossible request.", Toast.LENGTH_LONG).show();
    }
}
