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
import android.content.Intent;

// Terry Schmidt, CSC472, Fall 2015

public class FavchanActivity extends Activity {

    private RadioButton radioButtonLeft;
    private RadioButton radioButtonMiddle;
    private RadioButton radioButtonRight;
    private EditText labelField;
    private TextView userChannelText;
    private Button zeroButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button plusButton;
    private Button minusButton;
    private Button cancelButton;
    private Button saveButton;
    private ButtonClickListener btnClick;
    public Boolean leftRadioButtonSelected = false;
    public Boolean middleRadioButtonSelected = false;
    public Boolean rightRadioButtonSelected = false;
    private int currentChanNum = 0;
    private String channelUserIsEntering = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favchan);

        btnClick = new ButtonClickListener();
        radioButtonLeft = (RadioButton) findViewById(R.id.radioButtonLeft);
        radioButtonMiddle = (RadioButton) findViewById(R.id.radioButtonMiddle);
        radioButtonRight = (RadioButton) findViewById(R.id.radioButtonRight);
        labelField = (EditText) findViewById(R.id.labelField);
        userChannelText = (TextView) findViewById(R.id.userChannelText);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        int[] buttonIDlist = {
                R.id.zeroButton,
                R.id.oneButton,
                R.id.twoButton,
                R.id.threeButton,
                R.id.fourButton,
                R.id.fiveButton,
                R.id.sixButton,
                R.id.sevenButton,
                R.id.eightButton,
                R.id.nineButton,
                R.id.plusButton,
                R.id.minusButton,
                R.id.cancelButton,
                R.id.saveButton
        };

        for (int id : buttonIDlist) {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick); // set all relevant buttons as onClickListeners.
        }

    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.plusButton:  plusPressed(); break; // when user presses plus, invoke appropriate function.
                case R.id.minusButton:   minusPressed(); break; // when user presses minus, invoke appropriate function.
                case R.id.cancelButton:  cancelPressed();  break; // when user presses cancel, invoke appropriate function.
                case R.id.saveButton:   savePressed(); break; // when user pressed save, invoke appropriate function.
                default: numberPressed(v); break; // default case is when the user pressed a number button (0 - 9).
            }
        }
    }

    private void plusPressed() { // when user press +, check that chanNum can be incremented.  If so, increment, set appropriate string in UI.
        if (currentChanNum <= 998) {
            currentChanNum++;
            String tmp = Integer.toString(currentChanNum);
            if (tmp.length() == 1) {
                userChannelText.setText("00" + Integer.toString(currentChanNum));
            }

            if (tmp.length() == 2) {
                userChannelText.setText("0" + Integer.toString(currentChanNum));
            }

            if (tmp.length() == 3) {
                userChannelText.setText(Integer.toString(currentChanNum));
            }
        }
    }

    private void minusPressed() { // when user presses -, check that chanNum can be decremented.  If so, decrement, set appropriate string in UI.
        if (currentChanNum >= 2) {
            currentChanNum--;
            String tmp = Integer.toString(currentChanNum);
            if (tmp.length() == 1) {
                userChannelText.setText("00" + Integer.toString(currentChanNum));
            }

            if (tmp.length() == 2) {
                userChannelText.setText("0" + Integer.toString(currentChanNum));
            }

            if (tmp.length() == 3) {
                userChannelText.setText(Integer.toString(currentChanNum));
            }
        }
    }

    private void cancelPressed() { // if user presses cancel, discard anything they have done and finish the activity.
        finish();
    }

    private void savePressed() { // check booleans to find the selected radio button, check label length, validate channel num.  Display toasts if there are issues.
        // if there are no issues, make a new intent, attach the data needed in the main activity, setResult the intent, and finish() this activity.
        Intent data = new Intent();
        if (leftRadioButtonSelected == true && middleRadioButtonSelected == false && rightRadioButtonSelected == false) {
            if (labelField.getText().length() >= 2 && labelField.getText().length() <= 4) {
                if (Integer.parseInt(userChannelText.getText().toString()) >= 1 && Integer.parseInt(userChannelText.getText().toString()) <= 999) {
                    data.putExtra("Label", labelField.getText());
                    data.putExtra("Position", "Left");
                    data.putExtra("Channel", userChannelText.getText());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Toast.makeText(this, "Channel number cannot be 0.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Label must be 2-4 characters long.", Toast.LENGTH_LONG).show();
            }
        } else if (middleRadioButtonSelected == true && leftRadioButtonSelected == false && rightRadioButtonSelected == false) {
            if (labelField.getText().length() >= 2 && labelField.getText().length() <= 4) {
                if (Integer.parseInt(userChannelText.getText().toString()) >= 1 && Integer.parseInt(userChannelText.getText().toString())<= 999) {
                    data.putExtra("Label", labelField.getText());
                    data.putExtra("Position", "Middle");
                    data.putExtra("Channel", userChannelText.getText());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Toast.makeText(this, "Channel number cannot be 0.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Label must be 2-4 characters long.", Toast.LENGTH_LONG).show();
            }
        } else if (rightRadioButtonSelected == true && leftRadioButtonSelected == false && middleRadioButtonSelected == false) {
            if (labelField.getText().length() >= 2 && labelField.getText().length() <= 4) {
                if (Integer.parseInt(userChannelText.getText().toString()) >= 1 && Integer.parseInt(userChannelText.getText().toString()) <= 999) {
                    data.putExtra("Label", labelField.getText());
                    data.putExtra("Position", "Right");
                    data.putExtra("Channel", userChannelText.getText());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Toast.makeText(this, "Channel number cannot be 0.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Label must be 2-4 characters long.", Toast.LENGTH_LONG).show();
            }
        }

        if (rightRadioButtonSelected == false && leftRadioButtonSelected == false && middleRadioButtonSelected == false) {
            Toast.makeText(this, "You must select one of the radio buttons.", Toast.LENGTH_LONG).show();
        }
    }

    private void numberPressed(View v) { // get the number the user pressed, check if user has pressed a sequence of 3 numbers.  If so, attempt to validate it.
        // if its not valid, reset the string for the next sequence of 3 numbers.
        String numPressed = ((Button) v).getText().toString();
        channelUserIsEntering += numPressed;

        if (channelUserIsEntering.length() == 3) {
            if (Integer.parseInt(channelUserIsEntering) <= 999 && Integer.parseInt(channelUserIsEntering) >= 1) {
                userChannelText.setText(channelUserIsEntering);
                currentChanNum = Integer.parseInt(userChannelText.getText().toString());
                channelUserIsEntering = "";
            } else {
                channelUserIsEntering = "";
            }
        }
    }

    public void onRadioButtonClicked(View view) { // invoked when a user selects a radio button.  Set appropriate boolean values as a result.
        RadioButton radioButton = (RadioButton) view;
        if (radioButton.getText().equals("Left")) {
            System.out.println("Left radio pressed");
            leftRadioButtonSelected = true;
            middleRadioButtonSelected = false;
            rightRadioButtonSelected = false;
        }

        if (radioButton.getText().equals("Middle")) {
            System.out.println("Middle radio pressed");
            middleRadioButtonSelected = true;
            leftRadioButtonSelected = false;
            rightRadioButtonSelected = false;
        }

        if (radioButton.getText().equals("Right")) {
            System.out.println("Right radio pressed");
            rightRadioButtonSelected = true;
            middleRadioButtonSelected = false;
            leftRadioButtonSelected = false;
        }
    }
}
