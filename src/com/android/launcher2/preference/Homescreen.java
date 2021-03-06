/*
 * Copyright (C) 2013 ParanoidAndroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher2.preference;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.android.launcher.R;
import com.android.launcher2.LauncherModel;

public class Homescreen extends LauncherPreferenceActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_homescreen);
        
        DoubleNumberPickerPreference mGrid = (DoubleNumberPickerPreference)findPreference("ui_homescreen_grid");
        mGrid.setDefault1(LauncherModel.getCellCountX());
        mGrid.setDefault2(LauncherModel.getCellCountY());
        
        NumberPickerPreference mScreens = (NumberPickerPreference)findPreference("ui_homescreen_screens");
        mScreens.setDefaultValue(PreferencesProvider.getNumberHomescreens());
        
        NumberPickerPreference mDefaultScreen = (NumberPickerPreference)findPreference("ui_homescreen_default_screen");
        mDefaultScreen.setDefaultValue(PreferencesProvider.getDefaultHomescreen(3));

        findPreference("ui_homescreen_indicator_position")
                .setOnPreferenceChangeListener(
                        new OnPreferenceChangeListener() {

                            @Override
                            public boolean onPreferenceChange(
                                    Preference preference, Object newValue) {
                                PreferencesProvider.setString(
                                        preference.getKey(), (String) newValue);
                                return true;
                            }
                        });
    }
}
