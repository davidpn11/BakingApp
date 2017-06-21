package com.android.pena.david.bakingapp;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import com.android.pena.david.bakingapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *  @Rule
 */

@RunWith(AndroidJUnit4.class)
public class RecipeClickTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickRecipeTest(){
        String[] recipe_names = {"Nutella Pie","Brownies","Yellow Cake","Cheesecake"};

        for(int i = 0; i< recipe_names.length;i++){
            onView(withId(R.id.recipes_list)).perform(scrollToPosition(i));
            SystemClock.sleep(2000);
            onView(withId(R.id.recipes_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                    .check(matches(withText(recipe_names[i])));

        Espresso.pressBack();
        }
    }



    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.android.pena.david.bakingapp", appContext.getPackageName());
    }
}
