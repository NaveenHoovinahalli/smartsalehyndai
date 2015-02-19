package com.hyundai.teli.smartsales.views;

import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class CheckBoxGroup {

    private ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();

    public void addCheckBox(CheckBox checkBox) {
        checkBoxList.add(checkBox);
    }

    public void removeCheckBox(int index) {
        checkBoxList.remove(index);
    }

    public void removeCheckBox(CheckBox checkBox) {
        checkBoxList.remove(checkBox);
    }

    public ArrayList<CheckBox> getCheckboxList() {
        return checkBoxList;
    }

    public boolean isCheckedAtLeastOne() {

        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                return true;
            }
        }

        return false;
    }

    public String getCheckedStateToString() {

        String checkedState = "";

        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                checkedState += "1";
            } else {
                checkedState += "0";
            }
        }
        return checkedState;
    }

    public void setChildCheckedState(String state) throws NullPointerException {
        if (state == null) {
            state = "";
        }

        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '0') {
                checkBoxList.get(i).setChecked(false);
            } else {
                checkBoxList.get(i).setChecked(true);
            }

        }
    }

}
