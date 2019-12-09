package com.algorithm.liftapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.algorithm.liftapp.views.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityActivityEspressoTest {

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.algorithm.liftapp", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void clickSenarioOne() throws Exception {
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);

        onView(withId(R.id.btn_start)).perform(click());
        Thread.sleep(2000);
    }

    @Test
    public void clickSenarioTwo() throws Exception {
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);

        onView(withId(R.id.btn_start)).perform(click());
        Thread.sleep(2000);
    }

    @Test
    public void clickSenarioThree() throws Exception {
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);

        onView(withId(R.id.btn_start)).perform(click());
        Thread.sleep(2000);
    }

    @Test
    public void clickSenarioFour() throws Exception {
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_down)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);
        onView(withId(R.id.list_lift)).perform(
                RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.btn_up)));
        Thread.sleep(1000);

        onView(withId(R.id.btn_start)).perform(click());
        Thread.sleep(2000);
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };

    }
}
