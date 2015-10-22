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
    private Boolean leftRadioButtonSelected = false;
    private Boolean middleRadioButtonSelected = false;
    private Boolean rightRadioButtonSelected = false;
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
            v.setOnClickListener(btnClick);
        }

    }

    private class ButtonClickListener implements OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.plusButton:  plusPressed(); break;
                case R.id.minusButton:   minusPressed(); break;
                case R.id.cancelButton:  cancelPressed();  break;
                case R.id.saveButton:   savePressed(); break;
                default: numberPressed(v); break;
            }
        }
    }

    private void plusPressed() {
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

    private void minusPressed() {
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

    private void cancelPressed() {

    }

    private void savePressed() {

    }

    private void numberPressed(View v) {
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

    public void onRadioButtonClicked(View view) {
        RadioButton radioButton = (RadioButton) view;
        if (radioButton.getText().equals("Left")) {
            leftRadioButtonSelected = true;
            middleRadioButtonSelected = false;
            rightRadioButtonSelected = false;
        } else if (radioButton.getText().equals("Middle")) {
            middleRadioButtonSelected = true;
            leftRadioButtonSelected = false;
            rightRadioButtonSelected = false;
        } else if (radioButton.getText().equals("Right")) {
            rightRadioButtonSelected = true;
            middleRadioButtonSelected = false;
            leftRadioButtonSelected = false;
        }
    }
























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favchan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
