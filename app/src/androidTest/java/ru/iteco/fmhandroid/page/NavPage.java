package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NavPage {

    ViewInteraction toastButton = onView(withId(R.id.main_menu_image_button));

    ViewInteraction newsPageButton = onView(withText("News"));
    ViewInteraction mainPageButton = onView(withText("Main"));
    ViewInteraction aboutPageButton = onView(withText("About"));

    public void goToNewsPage() {
        toastButton.check(matches(isDisplayed()));
        toastButton.perform(click());

        newsPageButton.check(matches(isDisplayed()));
        newsPageButton.perform(click());
    }

    public void goToAboutPage() {
        toastButton.check(matches(isDisplayed()));
        toastButton.perform(click());

        aboutPageButton.check(matches(isDisplayed()));
        aboutPageButton.perform(click());
    }

    public void goToMainPage() {
        toastButton.check(matches(isDisplayed()));
        toastButton.perform(click());

        mainPageButton.check(matches(isDisplayed()));
        mainPageButton.perform(click());
    }

    public boolean checkIfToastButtonVisible(){
        try {
            toastButton.check(matches(isDisplayed()));
            return true; // Элемент найден и виден
        } catch (NoMatchingViewException e) {
            return false; // Элемент не найден или не виден
        }
    }


}
