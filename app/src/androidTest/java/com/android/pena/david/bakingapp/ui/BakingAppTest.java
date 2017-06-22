package com.android.pena.david.bakingapp.ui;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import com.android.pena.david.bakingapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BakingAppTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecipeTest(){
        String[] recipe_names = {"Nutella Pie","Brownies","Yellow Cake","Cheesecake"};

        for(int i = 0; i< recipe_names.length;i++){
            onView(withId(R.id.recipes_list)).perform(scrollToPosition(i));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withId(R.id.recipes_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                    .check(matches(withText(recipe_names[i])));

            Espresso.pressBack();
        }
    }

    @Test
    public void exoPlayerTest() {
        onView(withId(R.id.recipes_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        ViewInteraction exoPause = onView(withId(R.id.exo_pause)).check(matches(isDisplayed()));

        waitAction(2000);

        exoPause.perform(click());
        onView(withId(R.id.exo_prev)).perform(click());

        waitAction(2000);

        onView(withId(R.id.exo_play)).perform(click());
        onView(withId(R.id.exo_ffwd)).perform(click());

        waitAction(2000);

        onView(allOf(withId(R.id.refresh_button), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(click());

    }


    private void waitAction(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
